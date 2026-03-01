package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CreateConversationRequest(
    @SerialName("localId")
    val localId: String,
    @Contextual val localCreatedAt: Instant,
    @SerialName("participants")
    val participants: List<ParticipantRequest>,
    @SerialName("name")
    val name: String?
)