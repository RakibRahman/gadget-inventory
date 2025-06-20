package com.example.model

import com.example.dto.User
import com.example.dto.UserRole
import jakarta.inject.Singleton
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Singleton
open class UserService {
    private val userMap = ConcurrentHashMap<String, User>()

    init {
        addUser(User(id = "1", name = "Alice", email = "alice@example.com", role = UserRole.ADMIN))
        addUser(User(id="2",name = "Bob", email = "bob@example.com", role = UserRole.MODERATOR))
        addUser(User(id = "3", name = "Charlie", email = "charlie@example.com"))
    }

    fun addUser(user: User){
        userMap[user.id as String] = user
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