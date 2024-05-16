package com.example.demo.services

import com.example.demo.model.user.User
import com.example.demo.model.user.UserRepository
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class JwtTokenService(
        private val jwtDecoder: JwtDecoder,
        private val jwtEncoder: JwtEncoder,
        private val userRepository: UserRepository,
) {
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
                .subject(user.email)
                .claim("userId", user.id)
                .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): User? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as String
            val user = userRepository.findById(userId).get()
            return user;
        } catch (e: Exception) {
            null
        }
    }
}