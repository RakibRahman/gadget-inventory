package com.example.exception

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ApiError(
    val message: String,
    val path: String
)
