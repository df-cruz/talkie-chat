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
    val avatar: String? = null,
    val createdAt: Instant,
    val serverCreatedAt: Instant? = null,
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null
)