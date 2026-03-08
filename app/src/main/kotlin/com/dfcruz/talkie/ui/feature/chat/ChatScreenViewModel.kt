package com.dfcruz.talkie.ui.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.model.Conversation
import com.dfcruz.talkie.domain.model.Message
import com.dfcruz.talkie.domain.model.MessageType
import com.dfcruz.talkie.domain.repository.ConversationRepository
import com.dfcruz.talkie.domain.repository.MessageRepository
import com.dfcruz.talkie.domain.usecase.GetCurrentUserUseCase
import com.dfcruz.talkie.ui.feature.chat.model.MessageAuthor
import com.dfcruz.talkie.ui.feature.chat.model.MessageContent
import com.dfcruz.talkie.ui.feature.chat.model.MessageGroupPosition
import com.dfcruz.talkie.ui.feature.chat.model.MessageUiModel
import com.dfcruz.talkie.util.date.DateFormatPatterns
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
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID
import kotlin.time.Clock

class ChatScreenViewModel(
    private val messagesRepository: MessageRepository,
    private val conversationId: String,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val conversationRepository: ConversationRepository,
) : ViewModel() {

    private val formatter = SimpleDateFormat(DateFormatPatterns.SHORT_TIME, Locale.getDefault())

    private var sendMessageJob: Job? = null

    val uiState: StateFlow<ChatScreenUiState> = combine(
        getMessages(),
        getConversation(),
    ) { messages, conversation ->
        ChatScreenUiState(
            messages = messages,
            conversationName = conversation?.getConversationName().orEmpty(),
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
                    MessageUiModel(
                        id = message.id,
                        content = MessageContent.Text(message.text.orEmpty()),
                        createdAt = formatter.format(message.createdAt?.toEpochMilliseconds()),
                        author = MessageAuthor.CurrentUser,
                        groupPosition = MessageGroupPosition.First
                    )
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
                    senderId = getCurrentUserUseCase().id,
                    type = MessageType.TEXT,
                    text = message,
                    conversationId = conversationId,
                    createdAt = Clock.System.now(),
                )
            )
        }
    }
}