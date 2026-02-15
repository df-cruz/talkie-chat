package com.dfcruz.talkie.ui.feature.createconversation

sealed class CreateConversationEvent {
    data class ConversationCreated(val conversationId: String) : CreateConversationEvent()
    data class ShowError(val message: String) : CreateConversationEvent()
}