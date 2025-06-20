package com.example.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Get
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Message(val message: String)

@Controller("/hello")
class HelloController{
    @Get
    @Produces(MediaType.TEXT_JSON)
    fun index() = Message("This world shall know Mircronaut")
}