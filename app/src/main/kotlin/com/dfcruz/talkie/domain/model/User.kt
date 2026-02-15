package com.dfcruz.talkie.domain.model

import java.util.Date

data class User(
    val id: String = "",
    val name: String = "",
    val contact: String = "",
    val avatar: String = "",
    val isOnline: Boolean = false,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val lastSeenAt: Date? = null,
)