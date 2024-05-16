package com.example.demo.model.employee

import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Employee, String> {
    fun findByEmployeeId(employeeId: String): Employee?
}