package com.example.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Message(val message: String)
