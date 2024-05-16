package com.example.demo.errors

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class ResponseException(
        val httpStatus: HttpStatus,
        val errorMessage: String,
        val errorCode: Int? = null
) : ResponseStatusException(httpStatus, errorMessage) {
    fun getResponseBody(): Map<String, Any> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
        return mapOf(
                "timestamp" to ZonedDateTime.now().format(formatter),
                "httpStatus" to this.httpStatus.value(),
                "errorMessage" to this.errorMessage,
                "errorCode" to errorCode.toString()
        )
    }
}
