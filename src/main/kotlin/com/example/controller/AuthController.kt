package com.example.controller

import com.example.service.UserService
import com.example.util.ApiRoutes
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller(ApiRoutes.AUTH)
open class AuthController(private val userService: UserService) {
    @Post
    @Produces(MediaType.APPLICATION_JSON)
    fun login(email: String) {
        TODO()
    }
}