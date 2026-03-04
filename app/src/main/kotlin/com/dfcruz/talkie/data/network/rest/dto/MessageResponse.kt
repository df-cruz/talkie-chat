package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class MessageResponse(
    @SerialName("id")
    val id: String,
    @SerialName("conversationId")
    val conversationId: String,
    @SerialName("senderId")
    val senderId: String,
    @SerialName("type")
    val type: MessageType,
    @SerialName("text")
    val text: String?,
    @SerialName("serverCreatedAt")
    @Contextual val serverCreatedAt: Instant,
    @SerialName("updatedAt")
    @Contextual val updatedAt: Instant? = null,
    @SerialName("deletedAt")
    @Contextual val deletedAt: Instant? = null
)