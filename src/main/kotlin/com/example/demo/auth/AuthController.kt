package com.example.demo.auth
import com.example.demo.auth.response.LoginResponse
import com.example.demo.auth.request.LoginRequest
import com.example.demo.auth.request.SignUpRequest
import com.example.demo.auth.response.SignUpResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public-api")
class AuthController(private val authService: AuthService) {
    @PostMapping("/login")
    @ResponseBody
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        return this.authService.login(request)
    }

    @PostMapping("/signup")
    @ResponseBody
    fun signup(@RequestBody request: SignUpRequest): SignUpResponse {
        return this.authService.signUp(request)
    }
}