package com.dfcruz.talkie.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dfcruz.talkie.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    fun getUserFlow(userId: String): Flow<UserEntity>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUser(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE phoneNumber = :phoneNumber LIMIT 1")
    suspend fun getUserByPhoneNumber(phoneNumber: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun delete(userId: String)
}