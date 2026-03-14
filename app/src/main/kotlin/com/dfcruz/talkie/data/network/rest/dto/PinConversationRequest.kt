package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PinConversationRequest(
    @SerialName("pinned")
    val pinned: Boolean
)