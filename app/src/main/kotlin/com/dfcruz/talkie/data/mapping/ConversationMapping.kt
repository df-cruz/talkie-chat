package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.ConversationEntity
import com.dfcruz.talkie.domain.model.Conversation

fun ConversationEntity.toDomain(): Conversation {
    return Conversation(
        id = this.id,
        ownerId = this.ownerId.orEmpty(),
        avatarUrl = this.avatar.orEmpty(),
        name = this.name.orEmpty(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun Conversation.toEntity(): ConversationEntity {
    return ConversationEntity(
        id = this.id,
        ownerId = this.ownerId,
        name = this.name,
        avatar = this.avatarUrl,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}