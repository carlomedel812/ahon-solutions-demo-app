package com.example.demo.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull
import java.util.*
@Entity
@Table(name = "user", schema = "public")
data class User(
        @Id
        @Column(name = "id")
        val id: String = UUID.randomUUID().toString(),

        @NotNull
        @Column(name = "email")
        val email: String,

        @NotNull
        @Column(name = "password_hash")
        val passwordHash: String,

        @NotNull
        @Column(name = "salt")
        val salt: String
) {
        constructor(): this("", "",  "", "")
}
