package com.example.demo.auth.request

data class SignUpRequest(
        val email: String,
        val password: String,
        val confirmPassword: String
)