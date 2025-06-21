package com.example.dto

import io.micronaut.serde.annotation.Serdeable

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
    var role: UserRole = UserRole.USER
)

@Serdeable
data class UpdateUserRequest(
    var name: String? = null,
    var email: String? = null,
    var role: UserRole? = UserRole.USER
)