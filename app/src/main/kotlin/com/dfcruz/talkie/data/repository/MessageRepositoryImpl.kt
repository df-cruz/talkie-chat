package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.database.dao.MessageDao
import com.dfcruz.talkie.data.mapping.toDomain
import com.dfcruz.talkie.data.mapping.toEntity
import com.dfcruz.talkie.domain.model.Message
import com.dfcruz.talkie.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRepositoryImpl(
    private val messageDao: MessageDao,
) : MessageRepository {
    override fun getMessagesFlow(conversationId: String): Flow<List<Message>> {
        return messageDao.getMessagesFlow(conversationId)
            .map { messages -> messages.map { it.toDomain() } }
    }

    override suspend fun getMessages(conversationId: String): List<Message> {
        return messageDao.getMessages(conversationId).map { it.toDomain() }
    }

    override suspend fun getMessage(id: String) {
        messageDao.getMessage(id)
    }

    override suspend fun updateMessage(message: Message) {
        messageDao.update(message.toEntity())
    }

    override suspend fun sendMessage(message: Message) {
        messageDao.insert(message.toEntity())
    }

    override suspend fun deleteMessage(messageId: String) {
        messageDao.delete(messageId)
    }
}