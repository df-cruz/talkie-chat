package com.dfcruz.talkie.ui.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.repository.MessageRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChatScreenViewModel(
    messagesRepository: MessageRepository,
    conversationId: String,
) : ViewModel() {

    val messages: StateFlow<List<MessageUiModel>> =
        messagesRepository.getMessagesFlow(conversationId)
            .map {
                it.map { message ->
                    MessageUiModel(id = message.id, message = message.text)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = listOf()
            )

}