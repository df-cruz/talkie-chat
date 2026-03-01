package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CreateMessageRequest(
    @SerialName("localId")
    val localId: String,
    @SerialName("localCreatedAt")
    @Contextual val localCreatedAt: Instant,
    @SerialName("conversationId")
    val conversationId: String,
    @SerialName("type")
    val type: MessageType,
    @SerialName("text")
    val text: String
)