package com.dfcruz.talkie.ui.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.model.Conversation
import com.dfcruz.talkie.domain.model.Message
import com.dfcruz.talkie.domain.repository.ConversationRepository
import com.dfcruz.talkie.domain.repository.MessageRepository
import com.dfcruz.talkie.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class ChatScreenViewModel(
    private val messagesRepository: MessageRepository,
    private val conversationId: String,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val conversationRepository: ConversationRepository,
) : ViewModel() {

    private var sendMessageJob: Job? = null

    val uiState: StateFlow<ChatScreenUiState> = combine(
        getMessages(),
        getConversation(),
    ) { messages, conversation ->
        ChatScreenUiState(
            messages = messages,
            conversationName = conversation?.name.orEmpty(),
        )
    }
        .catch { e ->
            Log.e("ChatScreenViewModel", "ui state error", e)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChatScreenUiState(),
        )


    private fun getConversation(): Flow<Conversation?> {
        return flow { emit(conversationRepository.getConversation(conversationId)) }
    }

    private fun getMessages(): Flow<List<MessageUiModel>> {
        return messagesRepository.getMessagesFlow(conversationId)
            .map {
                it.map { message ->
                    MessageUiModel(id = message.id, message = message.text)
                }
            }
    }

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