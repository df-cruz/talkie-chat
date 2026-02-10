package com.dfcruz.talkie.domain.model

import java.util.Date

data class Message(
    val id: String = "",
    val conversationId: String = "",
    val authorId: String = "",
    val text: String = "",
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val deletedAt: Date? = null,
    val silent: Boolean = false
)