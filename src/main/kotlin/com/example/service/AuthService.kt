package com.example.service

import com.example.model.AuthEntity
import com.example.model.AuthProvider
import com.example.model.UserEntity
import com.example.repository.AuthRepository
import com.example.util.HashUtil
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Singleton
open class AuthService(private val authRepository: AuthRepository) {
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


}