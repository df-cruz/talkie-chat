package com.dfcruz.talkie.domain.repository

import com.dfcruz.talkie.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserFlow(userId: String): Flow<User>
    suspend fun getUser(userId: String): User?
    suspend fun getUserByContact(contact: String): User?
    suspend fun addUser(user: User): User
    suspend fun updateUser(user: User)
    suspend fun removeUser(userId: String)
}