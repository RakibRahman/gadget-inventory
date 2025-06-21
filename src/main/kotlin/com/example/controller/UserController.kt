package com.example.controller

import com.example.dto.Message
import com.example.dto.UpdateUserRequest
import com.example.dto.User
import com.example.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.validation.Valid

@Controller("/user")
class UserController(private val userService: UserService) {

    @Get("/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Collection<User> {
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

    @Patch("/{id}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun updateUserById(@PathVariable id: String, @Body @Valid payload: UpdateUserRequest): HttpResponse<Any> {
        val updateUser = userService.updateUser(id, payload)
        return if (updateUser != null) {
            HttpResponse.ok(updateUser)
        } else {
            HttpResponse.notFound(Message("User update failed"))
        }
    }

}