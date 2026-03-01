package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParticipantRequest(
    @SerialName("localId")
    val localId: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
)