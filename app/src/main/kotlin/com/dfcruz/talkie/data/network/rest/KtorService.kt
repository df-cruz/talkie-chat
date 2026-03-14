package com.dfcruz.talkie.data.network.rest

import com.dfcruz.talkie.data.network.rest.dto.ConversationResponse
import com.dfcruz.talkie.data.network.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.network.rest.dto.CreateMessageRequest
import com.dfcruz.talkie.data.network.rest.dto.MessageResponse
import com.dfcruz.talkie.data.network.rest.dto.MuteConversationRequest
import com.dfcruz.talkie.data.network.rest.dto.PinConversationRequest
import com.dfcruz.talkie.util.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorService(private val client: HttpClient) : TalkieService {

    override suspend fun getAllConversations(userId: String): Either<Throwable, List<ConversationResponse>> =
        safeRequest {
            client
                .get("conversations")
                .body<List<ConversationResponse>?>() ?: emptyList()
        }

    override suspend fun getConversationById(conversationId: String): Either<Throwable, ConversationResponse> =
        safeRequest {
            client.get("conversations/$conversationId").body()
        }

    override suspend fun createConversation(conversation: CreateConversationRequest): Either<Throwable, Unit> =
        safeRequest {
            client.post("conversations") {
                contentType(ContentType.Application.Json)
                setBody(conversation)
            }.body()
        }

    override suspend fun deleteConversation(conversationId: String): Either<Throwable, Unit> =
        safeRequest {
            client.delete("conversations/$conversationId")
        }

    override suspend fun pinConversation(
        conversationId: String,
        pin: Boolean
    ): Either<Throwable, Unit> = safeRequest {
        client.put("conversations/$conversationId/pin") {
            contentType(ContentType.Application.Json)
            setBody(PinConversationRequest(pin))
        }.body()
    }

    override suspend fun muteConversation(
        conversationId: String,
        mute: Boolean
    ): Either<Throwable, Unit> = safeRequest {
        client.put("conversations/$conversationId/mute") {
            contentType(ContentType.Application.Json)
            setBody(MuteConversationRequest(mute))
        }.body()
    }

    override suspend fun getMessagesByConversation(conversationId: String): Either<Throwable, List<MessageResponse>> =
        safeRequest {
            client
                .get("conversations/$conversationId/messages")
                .body<List<MessageResponse>?>() ?: emptyList()
        }

    override suspend fun getMessageById(
        conversationId: String,
        messageId: String
    ): Either<Throwable, MessageResponse> = safeRequest {
        client.get("conversations/$conversationId/messages/$messageId").body()
    }

    override suspend fun createMessage(
        conversationId: String,
        message: CreateMessageRequest
    ): Either<Throwable, Unit> = safeRequest {
        client.post("conversations/$conversationId/messages") {
            contentType(ContentType.Application.Json)
            setBody(message)
        }.body()
    }

    override suspend fun deleteMessage(
        conversationId: String,
        messageId: String
    ): Either<Throwable, Unit> = safeRequest {
        client.delete("conversations/$conversationId/messages/$messageId")
    }

    suspend inline fun <T> safeRequest(
        block: suspend () -> T,
    ): Either<Throwable, T> {
        return Either.catch {
            block()
        }
    }
}