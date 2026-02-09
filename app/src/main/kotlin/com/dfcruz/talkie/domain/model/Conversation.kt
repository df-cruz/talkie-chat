package com.dfcruz.talkie.domain.model

import java.util.Date

data class Conversation(
    val id: String = "",
    val ownerId: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val deletedAt: Date? = null,
)