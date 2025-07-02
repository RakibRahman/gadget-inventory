package com.example.controller

import com.example.dto.CreateUserRequest
import com.example.dto.LoginRequest
import com.example.exception.BadRequestException
import com.example.model.AuthProvider
import com.example.model.UserEntity
import com.example.service.AuthService
import com.example.util.ApiRoutes
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post
import jakarta.validation.Valid
import java.net.URI

@Controller(ApiRoutes.AUTH)
open class AuthController(private val authService: AuthService) {

    companion object {
        fun validatePassword(password: String) {
            val minLength = 16
            val specialCharRegex = Regex("[@#\$%^&+=*!]")

            if (password.length < minLength)
                throw BadRequestException("Password must be at least $minLength characters long")

            if (!password.any { it.isDigit() })
                throw BadRequestException("Password must include at least one digit")

            if (!specialCharRegex.containsMatchIn(password))
                throw BadRequestException("Password must include at least one special character (e.g., @, #, \$, etc.)")
        }
    }

    @Post("/login", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    open fun login(@Body @Valid payload: LoginRequest): HttpResponse<Any> {
        if (authService.isValidUser(payload.email)) {
            println("user exists")
        }
        return TODO("")
    }


    @Post("/signup", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    open fun signup(@Body @Valid payload: CreateUserRequest): HttpResponse<UserEntity> {
        if (!payload.password.isNullOrBlank()) {
            validatePassword(payload.password)
        }

        val newUser = authService.registerUser(payload);

        if (!payload.password.isNullOrBlank()) {
            authService.saveAuth(AuthProvider.EMAIL, newUser, payload.password)
        }

        val location = URI.create("/users/${newUser.id}")
        return HttpResponse.created(newUser).headers { it.location(location) }
    }

    @Patch("/reset-password", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun resetPassword() {
        TODO()
    }

    @Patch("/forget-password", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun forgetPassword() {
        TODO()
    }


}