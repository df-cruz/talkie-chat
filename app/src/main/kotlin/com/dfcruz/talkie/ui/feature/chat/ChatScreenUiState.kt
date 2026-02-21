package com.dfcruz.talkie.ui.feature.chat

data class ChatScreenUiState(
    val conversationName: String = "",
    val messages: List<MessageUiModel> = listOf()
)