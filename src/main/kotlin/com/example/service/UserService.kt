package com.example.service

import com.example.dto.UpdateUserRequest
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