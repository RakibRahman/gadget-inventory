package com.example.controller

import com.example.dto.Message
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Get

@Controller("/hello")
class HelloController{
    @Get
    @Produces(MediaType.TEXT_JSON)
    fun index() = Message("This world shall know Mircronaut")
}