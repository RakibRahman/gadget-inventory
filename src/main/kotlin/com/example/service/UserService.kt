package com.example.service

import com.example.dto.CreateUserRequest
import com.example.dto.UpdateUserRequest
import com.example.dto.User
import com.example.dto.UserRole
import jakarta.inject.Singleton
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Singleton
open class UserService {
    private val userMap = ConcurrentHashMap<String, User>()

    init {
        createUser(CreateUserRequest(name = "Alice", email = "alice@example.com", role = UserRole.ADMIN))
        createUser(CreateUserRequest(name = "Bob", email = "bob@example.com", role = UserRole.MODERATOR))
        createUser(CreateUserRequest(name = "Charlie", email = "charlie@example.com"))
        createUser(CreateUserRequest(name = "David", email = "david@example.com", role = UserRole.USER))
        createUser(CreateUserRequest(name = "Eva", email = "eva@example.com", role = UserRole.ADMIN))
        createUser(CreateUserRequest(name = "Frank", email = "frank@example.com", role = UserRole.MODERATOR))
        createUser(CreateUserRequest(name = "Grace", email = "grace@example.com"))
    }
    
    fun createUser(user: CreateUserRequest): User {
        val userId = UUID.randomUUID().toString()
        val newUser = User(
            id = userId,
            name = user.name,
            email = user.email,
            role = user.role ?: UserRole.USER
        )
        userMap[userId] = newUser
        return newUser
    }

    fun getAllUsers(): Collection<User> {
        return userMap.values
    }

    fun getUser(id: String): User? {
        return userMap[id]
    }

    fun updateUser(id: String, payload: UpdateUserRequest): User? {
        val existingUser = userMap[id] ?: return null

        val updatedUser = existingUser.copy(
            name = payload.name ?: existingUser.name,
            email = payload.email ?: existingUser.email,
            role = payload.role ?: existingUser.role
        )

        userMap[id] = updatedUser

        return updatedUser
    }

    fun removeUser(id: String): Boolean {
        return userMap.remove(id) != null
    }
}