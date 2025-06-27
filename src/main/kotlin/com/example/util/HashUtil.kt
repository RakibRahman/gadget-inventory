package com.example.util

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory


object HashUtil {
    val argon: Argon2 = Argon2Factory.create()

    private const val MEMORY_COST = 65536 // 64 MB
    private const val ITERATIONS = 3
    private const val PARALLELISM = 1

    fun hash(password: String): String? {
        return argon.hash(ITERATIONS, MEMORY_COST, PARALLELISM, password.toCharArray())
    }

    fun verifyPassword(password: String, hash: String): Boolean {
        return try {
            argon.verify(hash, password.toCharArray())
        } finally {
            argon.wipeArray(password.toCharArray())
        }

    }
}