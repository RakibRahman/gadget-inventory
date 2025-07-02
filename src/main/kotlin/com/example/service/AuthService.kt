package com.example.service

import com.example.dto.CreateUserRequest
import com.example.dto.UserRole
import com.example.model.AuthEntity
import com.example.model.AuthProvider
import com.example.model.UserEntity
import com.example.repository.AuthRepository
import com.example.repository.UserRepository
import com.example.util.HashUtil
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Singleton
open class AuthService(private val authRepository: AuthRepository, private val userRepository: UserRepository) {

    @Transactional
    open fun registerUser(payload: CreateUserRequest): UserEntity {
        val newUser = UserEntity(
            name = payload.name,
            email = payload.email,
            role = payload.role ?: UserRole.USER
        )

        return userRepository.save(newUser)
    }

    @Transactional
    open fun saveAuth(provider: AuthProvider, user: UserEntity, password: String? = "") {

        if (provider == AuthProvider.EMAIL && password?.isNotBlank() == true) {
            val auth = AuthEntity(
                hashedPassword = HashUtil.hash(password),
                providerUserId = user.id.toString(),
                user = user
            )
            authRepository.save(auth)
        } else {
            throw IllegalArgumentException("Password is required for EMAIL provider")
        }

    }

    fun isValidUser(email: String): Boolean {
        val user = userRepository.findByEmail(email).orElse(null)
        return user != null
    }

}