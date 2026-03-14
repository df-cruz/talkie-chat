package com.dfcruz.talkie.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.time.Instant

@Entity(
    tableName = "conversations",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.SET_NULL,
            deferred = true,
        )
    ],
    indices = [Index("ownerId")]
)
data class ConversationEntity(
    @PrimaryKey val id: String,
    val ownerId: String,
    val name: String? = null,
    val avatarUrl: String? = null,
    val createdAt: Instant ? = null,
    val serverCreatedAt: Instant? = null,
    val isPinned: Boolean = false,
    val isMuted: Boolean = false,
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null
)