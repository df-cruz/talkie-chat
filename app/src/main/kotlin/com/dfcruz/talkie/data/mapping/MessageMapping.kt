package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.MessageEntity
import com.dfcruz.talkie.domain.model.Message

fun MessageEntity.toDomain(): Message {
    return Message(
        id = this.id,
        conversationId = this.conversationId,
        text = this.text,
        authorId = this.authorId.orEmpty(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        silent = this.silent
    )
}

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = this.id,
        conversationId = this.conversationId,
        authorId = this.authorId,
        text = this.text,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        silent = this.silent
    )
}