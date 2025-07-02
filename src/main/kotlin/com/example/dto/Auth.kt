package com.example.dto

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Serdeable
data class LoginRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Password is required")
    val password: String
)
