package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CreateMessageRequest(
    @SerialName("id")
    val id: String,
    @SerialName("createdAt")
    @Contextual val createdAt: Instant,
    @SerialName("conversationId")
    val conversationId: String,
    @SerialName("type")
    val type: MessageType,
    @SerialName("text")
    val text: String
)