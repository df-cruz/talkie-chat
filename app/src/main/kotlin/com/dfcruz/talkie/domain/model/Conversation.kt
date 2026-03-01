package com.dfcruz.talkie.domain.model

import kotlin.time.Instant

data class Conversation(
    val id: String,
    val ownerId: String,
    val name: String? = null,
    val avatarUrl: String? = null,
    val participants: List<User> = listOf(),
    val createdAt: Instant,
    val updatedAt: Instant? = null,
    val deletedAt: Instant? = null,
) {

    fun getConversationName(): String = name ?: participants.joinToString(", ") { it.name }
}