package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class ConversationResponse(
    @SerialName("id")
    val id: String, // Server id
    @SerialName("localId")
    val localId: String, // Local conversation id
    @SerialName("ownerId")
    val ownerId: String, // Server user id
    @SerialName("name")
    val name: String,
    @SerialName("participants")
    val participants: List<ParticipantResponse>,
    @SerialName("serverCreatedAt")
    @Contextual val serverCreatedAt: Instant,
    @SerialName("serverUpdatedAt")
    @Contextual val serverUpdatedAt: Instant? = null,
    @SerialName("serverDeletedAt")
    @Contextual val serverDeletedAt: Instant? = null
)