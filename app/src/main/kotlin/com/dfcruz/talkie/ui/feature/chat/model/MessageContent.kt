package com.dfcruz.talkie.ui.feature.chat.model

sealed interface MessageContent {
    data class Text(val text: String) : MessageContent
}