package com.example.demo.model.employee

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
@Table(name = "employee", schema = "public")
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: String = UUID.randomUUID().toString(),

        @Column(name = "employee_id")
        val employeeId: String,

        @NotNull
        @Column(name = "name")
        val name: String,

        @NotNull
        @Column(name = "age")
        val age: Int
) {
    constructor() : this("", "", "", 0)
}
