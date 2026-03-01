package com.dfcruz.talkie.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.time.Instant

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ConversationEntity::class,
            parentColumns = ["id"],
            childColumns = ["conversationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("conversationId"), Index("senderId")]
)
data class MessageEntity(
    @PrimaryKey val id: String,
    val conversationId: String,
    val senderId: String,
    val type: MessageType,
    val text: String? = null,
    val createdAt: Instant,
    val serverCreatedAt: Instant? = null,
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null,
)