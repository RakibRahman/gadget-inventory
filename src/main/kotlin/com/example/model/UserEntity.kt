package com.example.model

import com.example.dto.UserRole
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.util.*

@Serdeable
@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var role: UserRole

)