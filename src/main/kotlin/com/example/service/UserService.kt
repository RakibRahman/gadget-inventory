package com.example.service

import com.example.dto.CreateUserRequest
import com.example.dto.UpdateUserRequest
import com.example.dto.UserRole
import com.example.model.UserEntity
import com.example.repository.UserRepository
import jakarta.annotation.PostConstruct
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import java.util.*

@Singleton
open class UserService(private val userRepository: UserRepository) {

    @PostConstruct
    fun init() {
        if (userRepository.count() == 0L) {
            createUser(CreateUserRequest(name = "Alice", email = "alice@example.com", role = UserRole.ADMIN))
            createUser(CreateUserRequest(name = "Bob", email = "bob@example.com", role = UserRole.MODERATOR))
            createUser(CreateUserRequest(name = "Charlie", email = "charlie@example.com"))
            createUser(CreateUserRequest(name = "David", email = "david@example.com", role = UserRole.USER))
            createUser(CreateUserRequest(name = "Eva", email = "eva@example.com", role = UserRole.ADMIN))
            createUser(CreateUserRequest(name = "Frank", email = "frank@example.com", role = UserRole.MODERATOR))
            createUser(CreateUserRequest(name = "Grace", email = "grace@example.com"))
        }
    }

    @Transactional
    open fun createUser(user: CreateUserRequest): UserEntity {

        val newUser = UserEntity(
            name = user.name,
            email = user.email,
            role = user.role ?: UserRole.USER
        )

        println("Payload role: ${newUser.role}")

        return userRepository.save(newUser)
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
}