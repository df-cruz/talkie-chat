package com.dfcruz.talkie.ui.feature.chat

import com.dfcruz.talkie.ui.feature.chat.model.MessageUiModel

data class ChatScreenUiState(
    val conversationName: String = "",
    val messages: List<MessageUiModel> = listOf()
)