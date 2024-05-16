package com.example.demo.model.employee

import org.springframework.stereotype.Service
import java.security.PrivateKey

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {

    fun findByEmployeeId(employeeId: String): Employee? {
        return employeeRepository.findByEmployeeId(employeeId)
    }
}