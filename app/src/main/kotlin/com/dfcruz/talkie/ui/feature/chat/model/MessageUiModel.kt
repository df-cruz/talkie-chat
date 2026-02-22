package com.dfcruz.talkie.ui.feature.chat.model

data class MessageUiModel(
    val id: String,
    val content: MessageContent,
    val createdAt: String,
    val author: MessageAuthor,
    val groupPosition: MessageGroupPosition = MessageGroupPosition.First,
)