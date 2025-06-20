package com.example.service

import com.example.dto.User
import com.example.dto.UserRole
import jakarta.inject.Singleton
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Singleton
open class UserService {
    private val userMap = ConcurrentHashMap<String, User>()

    init {
        addUser(User(name = "Alice", email = "alice@example.com", role = UserRole.ADMIN))
        addUser(User(name = "Bob", email = "bob@example.com", role = UserRole.MODERATOR))
        addUser(User(name = "Charlie", email = "charlie@example.com"))
    }

    fun addUser(user: User): User {
       val userId =  UUID.randomUUID().toString()
        val newUser = user.copy(id=userId)
        userMap[userId] = newUser
        return newUser
    }

    fun getAllUser(): Collection<User>{
        return userMap.values
    }

     fun getUser(id: String): User? {
        return  if(userMap.containsKey(id)) userMap.get(id) else null
    }

     fun updateUser(id: String,data: User): User?{
        if(userMap.containsKey(id)){
            userMap.put(id,data)
        return userMap.get(id)
        }
        return null
    }

     fun removeUser(id: String){
        if(userMap.containsKey(id))  userMap.remove(id)
    }
}