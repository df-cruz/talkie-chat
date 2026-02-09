package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.database.dao.ConversationDao
import com.dfcruz.talkie.data.mapping.toDomain
import com.dfcruz.talkie.data.mapping.toEntity
import com.dfcruz.talkie.domain.model.Conversation
import com.dfcruz.talkie.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversationRepositoryImpl(
    private val conversationDao: ConversationDao,
) : ConversationRepository {

    override fun getConversationsFlow(): Flow<List<Conversation>> {
        return conversationDao.getConversationsFlows()
            .map { conversations ->
                conversations.map { it.toDomain() }
            }
    }

    override suspend fun getConversation(conversationId: String): Conversation? {
        return conversationDao.getConversation(conversationId)?.toDomain()
    }

    override suspend fun createConversation(conversation: Conversation) {
        conversationDao.insert(conversation.toEntity())
    }

    override suspend fun updateConversation(conversation: Conversation) {
        conversationDao.update(conversation.toEntity())
    }

    override suspend fun deleteConversation(conversationId: String) {
        conversationDao.delete(conversationId)
    }

}