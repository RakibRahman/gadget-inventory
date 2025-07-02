package com.example.dto

import com.example.model.AuthProvider
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
    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    val role: UserRole? = UserRole.USER,
    val provider: AuthProvider? = AuthProvider.EMAIL,
    val password: String? = ""
)

@Serdeable
data class UpdateUserRequest(
    var name: String? = null,
    var email: String? = null,
    var role: UserRole? = null
)
