package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    @SerialName("id")
    val id: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
)