package com.example.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ErrorResponse(val message: String, val code: Int)