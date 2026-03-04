package com.dfcruz.talkie.data.network.rest.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class UserResponse(
    @SerialName("id")
    val id: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("avatarUrl")
    val avatarUrl: String,
    @SerialName("isOnline")
    val isOnline: Boolean = false,
    @SerialName("lastSeenAt")
    @Contextual val lastSeenAt: Instant? = null,
    @SerialName("serverCreatedAt")
    @Contextual val serverCreatedAt: Instant,
    @SerialName("updatedAt")
    @Contextual val updatedAt: Instant? = null,
    @SerialName("deletedAt")
    @Contextual val deletedAt: Instant? = null
)