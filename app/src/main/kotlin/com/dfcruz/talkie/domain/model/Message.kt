package com.dfcruz.talkie.domain.model

import kotlin.time.Instant

data class Message(
    val id: String,
    val conversationId: String,
    val senderId: String,
    val type: MessageType,
    val text: String? = null,
    val createdAt: Instant? = null,
    val serverCreatedAt: Instant? = null,
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null,
)