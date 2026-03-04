package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.ConversationEntity
import com.dfcruz.talkie.data.network.rest.dto.ConversationResponse
import com.dfcruz.talkie.domain.model.Conversation

fun ConversationEntity.toDomain(): Conversation {
    return Conversation(
        id = this.id,
        ownerId = this.ownerId,
        avatarUrl = this.avatarUrl.orEmpty(),
        name = this.name,
        createdAt = this.createdAt,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun Conversation.toEntity(): ConversationEntity {
    return ConversationEntity(
        id = this.id,
        ownerId = this.ownerId,
        name = this.name,
        avatarUrl = this.avatarUrl,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun ConversationResponse.toDomain(): Conversation {
    return Conversation(
        id = this.id,
        ownerId = this.ownerId,
        avatarUrl = this.avatarUrl,
        name = this.name,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun ConversationResponse.toEntity(): ConversationEntity {
    return ConversationEntity(
        id = this.id,
        ownerId = this.ownerId,
        name = this.name,
        avatarUrl = this.avatarUrl,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}