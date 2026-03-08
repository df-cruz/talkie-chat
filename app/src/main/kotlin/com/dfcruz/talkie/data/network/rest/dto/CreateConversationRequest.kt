package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CreateConversationRequest(
    @SerialName("id")
    val id: String,
    @SerialName("createdAt")
    @Contextual val createdAt: Instant? = null,
    @SerialName("participants")
    val participants: List<UserRequest>,
    @SerialName("name")
    val name: String?
)