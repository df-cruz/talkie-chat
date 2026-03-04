package com.dfcruz.talkie.data.mapping

import com.dfcruz.talkie.data.database.entity.MessageEntity
import com.dfcruz.talkie.data.network.rest.dto.MessageResponse
import com.dfcruz.talkie.domain.model.Message
import com.dfcruz.talkie.domain.model.MessageType
import com.dfcruz.talkie.data.database.entity.MessageType as MessageTypeEntity
import com.dfcruz.talkie.data.network.rest.dto.MessageType as MessageTypeResponse

fun MessageEntity.toDomain(): Message {
    return Message(
        id = this.id,
        conversationId = this.conversationId,
        senderId = this.senderId,
        type = this.type.toDomain(),
        text = this.text,
        createdAt = this.createdAt,
        serverCreatedAt = this.serverCreatedAt,
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
        serverCreatedAt = this.serverCreatedAt,
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

fun MessageResponse.toDomain(): Message {
    return Message(
        id = this.id,
        conversationId = this.conversationId,
        senderId = this.senderId,
        type = this.type.toDomain(),
        text = this.text,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}

fun MessageResponse.toEntity(): MessageEntity {
    return MessageEntity(
        id = this.id,
        conversationId = this.conversationId,
        senderId = this.senderId,
        type = this.type.toEntity(),
        text = this.text,
        serverCreatedAt = this.serverCreatedAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}

private fun MessageTypeResponse.toDomain(): MessageType = when (this) {
    MessageTypeResponse.TEXT -> MessageType.TEXT
}

private fun MessageTypeResponse.toEntity(): MessageTypeEntity = when (this) {
    MessageTypeResponse.TEXT -> MessageTypeEntity.TEXT
}