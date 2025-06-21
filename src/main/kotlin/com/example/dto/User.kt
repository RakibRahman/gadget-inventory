package com.example.dto

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

enum class UserRole {
    ADMIN,
    MODERATOR,
    USER
}

@Serdeable
data class User(
    val id: String? = null,
    var name: String,
    var email: String,
    var role: UserRole? = UserRole.USER
)

@Serdeable
data class CreateUserRequest(
    @field:NotBlank val name: String,
    @field:Email val email: String,
    val role: UserRole? = UserRole.USER
)

@Serdeable
data class UpdateUserRequest(
    var name: String? = null,
    var email: String? = null,
    var role: UserRole? = null
)