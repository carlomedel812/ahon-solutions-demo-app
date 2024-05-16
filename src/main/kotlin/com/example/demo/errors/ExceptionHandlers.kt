package com.example.demo.errors

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(ResponseException::class)
    @ResponseBody
    fun handleResponseException(exception: ResponseException, request: WebRequest): ResponseEntity<MutableMap<String, Any>> {
        // Get the response body from the ResponseException
        val responseBody = exception.getResponseBody() as MutableMap<String, Any>

        // Return the response body with the status code from the ResponseException
        return ResponseEntity.status(exception.httpStatus).body(responseBody)
    }
}