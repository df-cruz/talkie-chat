package com.dfcruz.talkie.ui.feature.conversationslist.model

sealed interface ConversationSubtitle {
    data class LastMessage(val text: String) : ConversationSubtitle
    data object Typing : ConversationSubtitle
}