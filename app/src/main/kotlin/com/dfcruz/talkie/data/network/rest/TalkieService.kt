package com.dfcruz.talkie.data.network.rest

import com.dfcruz.talkie.data.network.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.network.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.network.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.data.network.rest.dto.MessageResponse
import com.dfcruz.talkie.util.Either

interface TalkieService {
    suspend fun getAllConversations(userId: String): Either<Throwable, List<ConversationResponse>>
    suspend fun getConversationById(conversationId: String): Either<Throwable, ConversationResponse>
    suspend fun createConversation(conversation: CreateConversationRequest): Either<Throwable, Unit>
    suspend fun deleteConversation(conversationId: String): Either<Throwable, Unit>
    suspend fun pinConversation(conversationId: String, pin: Boolean): Either<Throwable, Unit>
    suspend fun muteConversation(conversationId: String, mute: Boolean): Either<Throwable, Unit>
    suspend fun getMessagesByConversation(conversationId: String): Either<Throwable, List<MessageResponse>>
    suspend fun getMessageById(
        conversationId: String,
        messageId: String
    ): Either<Throwable, MessageResponse>

    suspend fun createMessage(
        conversationId: String,
        message: CreateMessageRequest
    ): Either<Throwable, Unit>

    suspend fun deleteMessage(conversationId: String, messageId: String): Either<Throwable, Unit>
}