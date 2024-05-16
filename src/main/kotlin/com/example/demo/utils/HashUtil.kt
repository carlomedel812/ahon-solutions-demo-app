package com.example.demo.utils

import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*

interface Hash {
    val passwordHash: String
    val saltHash: String
}

fun generatePasswordHash(password: String, saltString: String? = null): Hash {
    val data = password.toByteArray()
    val salt = saltString?.toByteArray() ?: UUID.randomUUID().toString().toByteArray()

    val sha256 = MessageDigest.getInstance("SHA-256")
    sha256.update(salt)
    val hashValue = sha256.digest(data)

    return object : Hash {
        override val passwordHash: String
            get() = hashValue.toString(Charset.defaultCharset())

        override val saltHash: String
            get() = salt.toString(Charset.defaultCharset())
    }
}

fun isPasswordHashEqual(password: String, hashPassword: String, salt: String): Boolean {
    val hashPasswordInput = generatePasswordHash(password, salt).passwordHash
    return hashPasswordInput == hashPassword
}