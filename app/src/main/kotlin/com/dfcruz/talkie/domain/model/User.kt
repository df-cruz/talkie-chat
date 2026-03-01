package com.dfcruz.talkie.domain.model

import kotlin.time.Instant

data class User(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false,
    val createdAt: Instant,
    val updatedAt: Instant? = null,
    val lastSeenAt: Instant? = null,
)