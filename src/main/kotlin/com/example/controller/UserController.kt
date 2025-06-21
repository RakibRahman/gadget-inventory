package com.example.controller

import com.example.dto.CreateUserRequest
import com.example.dto.Message
import com.example.dto.UpdateUserRequest
import com.example.dto.User
import com.example.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.validation.Valid
import java.net.URI

@Controller("/user")
open class UserController(private val userService: UserService) {

    @Get("/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Collection<User> {
        return userService.getAllUsers()
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserById(id: String): HttpResponse<Any> {
        val user = userService.getUser(id)
        return if (user != null) {
            HttpResponse.ok(user)
        } else {
            HttpResponse.notFound(Message("User with id '$id' not found"))
        }
    }

    @Post(consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    open fun createUser(@Body @Valid payload: CreateUserRequest): HttpResponse<User> {
        val user = userService.addUser(payload)
        val location = URI.create("/user/${user.id}")
        return HttpResponse.created(user).headers { it.location(location) }
    }

    @Patch("/{id}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    open fun updateUserById(@PathVariable id: String, @Body @Valid payload: UpdateUserRequest): HttpResponse<Any> {
        val updateUser = userService.updateUser(id, payload)
        return if (updateUser != null) {
            HttpResponse.ok(updateUser)
        } else {
            HttpResponse.notFound(Message("User with id '$id' not found"))
        }
    }

    @Delete("/{id}")
    fun removeUserById(id: String): HttpResponse<Any> {
        val removed = userService.removeUser(id)

        return if (removed) {
            HttpResponse.noContent()
        } else {
            HttpResponse.notFound(Message("User not found"))
        }
    }
}