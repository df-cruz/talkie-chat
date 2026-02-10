package com.dfcruz.talkie.domain.repository

import com.dfcruz.talkie.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessagesFlow(conversationId: String): Flow<List<Message>>
    suspend fun getMessages(conversationId: String): List<Message>
    suspend fun getMessage(id: String)
    suspend fun updateMessage(message: Message)
    suspend fun sendMessage(message: Message)
    suspend fun deleteMessage(messageId: String)
}