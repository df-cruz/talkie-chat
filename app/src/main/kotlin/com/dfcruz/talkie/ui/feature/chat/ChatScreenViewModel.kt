package com.dfcruz.talkie.ui.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.model.Message
import com.dfcruz.talkie.domain.repository.MessageRepository
import com.dfcruz.talkie.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class ChatScreenViewModel(
    private val messagesRepository: MessageRepository,
    private val conversationId: String,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ViewModel() {

    private var sendMessageJob: Job? = null

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


    fun sendMessage(message: String) {
        if (message.isEmpty()) return
        if (sendMessageJob?.isActive == true) return
        sendMessageJob = viewModelScope.launch {
            messagesRepository.sendMessage(
                Message(
                    id = UUID.randomUUID().toString(),
                    authorId = getCurrentUserUseCase().id,
                    text = message,
                    conversationId = conversationId,
                    createdAt = Date()
                )
            )
        }
    }
}