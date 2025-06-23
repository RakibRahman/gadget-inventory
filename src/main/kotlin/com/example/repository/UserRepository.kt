package com.example.repository

import com.example.model.UserEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name: String, email: String): List<UserEntity>
    fun findByEmail(email: String): Optional<UserEntity>
}