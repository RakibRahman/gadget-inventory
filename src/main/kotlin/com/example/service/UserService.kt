package com.example.service

import com.example.dto.CreateUserRequest
import com.example.dto.UpdateUserRequest
import com.example.dto.UserRole
import com.example.exception.BadRequestException

import com.example.model.AuthProvider
import com.example.model.UserEntity
import com.example.repository.UserRepository
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.util.*

@Singleton
open class UserService(
    private val userRepository: UserRepository,
    private val authService: AuthService
) {

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


    @Transactional
    open fun createUser(payload: CreateUserRequest): UserEntity {

        if (!payload.password.isNullOrBlank()) {
            validatePassword(payload.password)
        }

        val newUser = UserEntity(
            name = payload.name,
            email = payload.email,
            role = payload.role ?: UserRole.USER
        )

        println("Payload role: ${newUser.role}")
        println("payload provider: ${payload.provider}")

        val savedUser = userRepository.save(newUser)

        if (!payload.password.isNullOrBlank()) {
            authService.saveAuth(AuthProvider.EMAIL, savedUser, payload.password)
        }

        return savedUser
    }

    fun getAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    fun getUser(id: UUID): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }

    @Transactional
    open fun updateUser(id: UUID, payload: UpdateUserRequest): UserEntity? {
        val existingUser = getUser(id)

        val updated = existingUser?.copy(
            name = payload.name ?: existingUser.name,
            email = payload.email ?: existingUser.email,
            role = payload.role ?: existingUser.role
        )

        return userRepository.update(updated)
    }

    fun deleteUser(id: UUID): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else false
    }

    fun searchUsersByNameOrEmail(query: String): List<UserEntity> {
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query)
    }
}