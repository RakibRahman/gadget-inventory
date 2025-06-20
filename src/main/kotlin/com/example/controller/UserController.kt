package com.example.controller

import com.example.dto.User
import com.example.model.UserService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.util.concurrent.ConcurrentHashMap

@Controller("/user")
class UserController(private val userService: UserService){

    @Get("/{id}")
    @Produces(MediaType.TEXT_JSON)
    fun getUserById(id: String): User? {
        val user = userService.getUser(id)
        return user
    }

}