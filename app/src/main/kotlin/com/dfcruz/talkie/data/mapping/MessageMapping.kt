package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.MessageEntity
import com.dfcruz.talkie.domain.model.Message
import com.dfcruz.talkie.domain.model.MessageType
import com.dfcruz.talkie.data.database.entity.MessageType as MessageTypeEntity

fun MessageEntity.toDomain(): Message {
    return Message(
        id = this.id,
        conversationId = this.conversationId,
        senderId = this.senderId,
        type = this.type.toDomain(),
        text = this.text,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = this.id,
        conversationId = this.conversationId,
        senderId = this.senderId,
        type = this.type.toEntity(),
        text = this.text,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}

private fun MessageType.toEntity() = when (this) {
    MessageType.TEXT -> MessageTypeEntity.TEXT
}

private fun MessageTypeEntity.toDomain() = when (this) {
    MessageTypeEntity.TEXT -> MessageType.TEXT
}