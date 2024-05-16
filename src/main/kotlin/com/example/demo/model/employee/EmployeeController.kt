package com.example.demo.model.employee

import com.example.demo.auth.AuthService
import com.example.demo.auth.request.LoginRequest
import com.example.demo.auth.request.SignUpRequest
import com.example.demo.auth.response.LoginResponse
import com.example.demo.auth.response.SignUpResponse
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/employee")
class EmployeeController(private val employeeService: EmployeeService) {
    @GetMapping("/{employeeId}")
    @ResponseBody
    fun getByEmployeeId(@PathVariable employeeId: String): Employee? {
        return this.employeeService.findByEmployeeId(employeeId)
    }
}