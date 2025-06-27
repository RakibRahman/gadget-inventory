package com.example.repository

import com.example.model.AuthEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AuthRepository : JpaRepository<AuthEntity, UUID> {
}