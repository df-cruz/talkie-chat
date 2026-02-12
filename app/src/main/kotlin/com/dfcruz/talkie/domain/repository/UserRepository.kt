package com.dfcruz.talkie.domain.repository

import com.dfcruz.talkie.domain.model.User

interface UserRepository {
    suspend fun getUser(userId: String): User?
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun removeUser(userId: String)
}