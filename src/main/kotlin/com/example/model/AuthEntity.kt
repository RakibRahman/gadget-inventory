package com.example.model

import jakarta.persistence.*
import java.util.*

enum class AuthProvider { EMAIL, FACEBOOK, GITHUB, GOOGLE, X, MICROSOFT }

@Entity
@Table(name = "auth_credentials")
data class AuthEntity(
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var provider: AuthProvider = AuthProvider.EMAIL,

    @Column(nullable = false)
    var providerUserId: String = "",

    @Column(nullable = true)
    var hashedPassword: String? = "",

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    var user: UserEntity
)
