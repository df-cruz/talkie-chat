package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.database.dao.UserDao
import com.dfcruz.talkie.data.mapping.toDomain
import com.dfcruz.talkie.data.mapping.toEntity
import com.dfcruz.talkie.domain.model.User
import com.dfcruz.talkie.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao,
) : UserRepository {

    override fun getUserFlow(userId: String): Flow<User> {
        return userDao.getUserFlow(userId).map { it.toDomain() }
    }

    override suspend fun getUser(userId: String): User? {
        return userDao.getUser(userId)?.toDomain()
    }

    override suspend fun getUserByContact(contact: String): User? {
        return userDao.getUserByPhoneNumber(contact)?.toDomain()
    }

    override suspend fun addUser(user: User): User {
        userDao.insert(user.toEntity())
        return user
    }

    override suspend fun updateUser(user: User) {
        userDao.update(user.toEntity())
    }

    override suspend fun removeUser(userId: String) {
        userDao.delete(userId)
    }
}