package com.example.demo.auth

import com.example.demo.auth.response.LoginResponse
import com.example.demo.auth.request.LoginRequest
import com.example.demo.auth.request.SignUpRequest
import com.example.demo.auth.response.SignUpResponse
import com.example.demo.errors.ResponseException
import com.example.demo.model.user.User
import com.example.demo.model.user.UserRepository
import com.example.demo.services.JwtTokenService
import com.example.demo.utils.generatePasswordHash
import com.example.demo.utils.isPasswordHashEqual
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class AuthService(
        val userRepository: UserRepository,
        val jwtTokenService: JwtTokenService
) {

    fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email)
                ?: throw ResponseException(
                        HttpStatus.BAD_REQUEST,
                        "Email does not exists",
                        90
                )

        if (!isPasswordHashEqual(request.password, user.passwordHash, user.salt)) {
            throw ResponseException(
                    HttpStatus.BAD_REQUEST,
                    "Incorrect password",
                    91
            )
        }

        val accessToken = generateAccessToken(user)
        return LoginResponse(accessToken = accessToken)
    }

    fun signUp(request: SignUpRequest): SignUpResponse {
        val userExists = userRepository.findByEmail(request.email) != null

        if (userExists) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email address already exists.",
            )
        }

        if (request.password != request.confirmPassword) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password do not match.",
            )
        }

        val hash = generatePasswordHash(request.password)
        val newUser = User(email = request.email, passwordHash = hash.passwordHash, salt = hash.saltHash)
        userRepository.save(newUser)

        val accessToken = generateAccessToken(newUser)
        return SignUpResponse(accessToken = accessToken)
    }

    private fun generateAccessToken(user: User): String {
        return jwtTokenService.createToken(user)
    }
}