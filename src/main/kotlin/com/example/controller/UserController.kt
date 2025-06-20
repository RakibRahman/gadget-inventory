package com.example.controller

import com.example.dto.Message
import com.example.dto.User
import com.example.model.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import jakarta.validation.Valid

@Controller("/user")
class UserController(private val userService: UserService){

    @Get("/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Collection<User>{
        return userService.getAllUser()
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(id: String): HttpResponse<Any> {
        val user = userService.getUser(id)
        return if (user != null) {
            HttpResponse.ok(user)
        } else {
            HttpResponse.notFound(Message("User not found"))
        }
    }

    @Post(consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun createUser(@Body @Valid payload: User): HttpResponse<User> {
       val user = userService.addUser(payload)
        return HttpResponse.created(user)
    }

}