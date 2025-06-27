package com.example.util

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory


object PasswordHasher {
    val argon: Argon2 = Argon2Factory.create()

    // Tune these parameters based on your environment
    private const val MEMORY_COST = 65536 // 64 MB
    private const val ITERATIONS = 3
    private const val PARALLELISM = 1

    fun hash(password: String): String? {
        return argon.hash(ITERATIONS, MEMORY_COST, PARALLELISM, password.toCharArray())
    }

    fun verifyPassword(password: String): Boolean {
        return try {
            val hashedPassword = hash(password)
            argon.verify(hashedPassword, password.toCharArray())
        } finally {
            argon.wipeArray(password.toCharArray())
        }

    }
}