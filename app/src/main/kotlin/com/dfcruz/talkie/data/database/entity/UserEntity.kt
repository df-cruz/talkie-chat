package com.dfcruz.talkie.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Instant

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val phoneNumber: String,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false,
    val createdAt: Instant,
    val serverCreatedAt: Instant? = null,
    val updatedAt: Instant? = null,
    val lastSeenAt: Instant? = null
)