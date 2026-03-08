package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.data.database.dao.ConversationDao
import com.dfcruz.talkie.data.database.dao.UserDao
import com.dfcruz.talkie.data.database.entity.ConversationMemberEntity
import com.dfcruz.talkie.data.mapping.toDomain
import com.dfcruz.talkie.data.mapping.toEntity
import com.dfcruz.talkie.data.network.rest.TalkieService
import com.dfcruz.talkie.data.network.rest.dto.CreateConversationRequest
import com.dfcruz.talkie.data.network.rest.dto.UserRequest
import com.dfcruz.talkie.domain.model.Conversation
import com.dfcruz.talkie.domain.repository.ConversationRepository
import com.dfcruz.talkie.util.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversationRepositoryImpl(
    private val conversationDao: ConversationDao,
    private val talkieService: TalkieService,
) : ConversationRepository {

    override fun getConversationsFlow(): Flow<List<Conversation>> {
        return conversationDao.getConversationsFlows()
            .map { conversations ->
                conversations.map { it.toDomain() }
            }
    }

    override suspend fun fetchConversation(id: String) {
        talkieService.getConversationById(id)
    }

    override suspend fun fetchConversations(userId: String) {
        talkieService.getAllConversations(userId).ifRight { conversations ->
            val entities = conversations.map { it.toEntity() }
            val members = conversations.flatMap { conv ->
                conv.participants.map {
                    ConversationMemberEntity(
                        conversationId = conv.id,
                        userId = it.id,
                    )
                }
            }
            conversationDao.upsertConversations(entities, members)
        }
    }

    override suspend fun getConversation(conversationId: String): Conversation? {
        return conversationDao.getConversation(conversationId)?.toDomain()
    }

    override suspend fun createConversation(conversation: Conversation): Conversation? {
        val members = conversation.participants.map {
            ConversationMemberEntity(
                conversationId = conversation.id,
                userId = it.id,
            )
        }

        Either.catch {
            conversationDao.createConversationWithMembers(conversation.toEntity(), members)
        }.ifRight {
            talkieService.createConversation(
                CreateConversationRequest(
                    id = conversation.id,
                    createdAt = conversation.createdAt,
                    participants = conversation.participants.map {
                        UserRequest(
                            id = it.id,
                            displayName = it.name,
                            phoneNumber = it.phoneNumber,
                        )
                    },
                    name = conversation.name
                )
            )
        }

        return getConversation(conversation.id)
    }

    override suspend fun updateConversation(conversation: Conversation) {
        conversationDao.update(conversation.toEntity())
    }

    override suspend fun deleteConversation(conversationId: String) {
        Either.catch { conversationDao.delete(conversationId) }
            .ifRight { talkieService.deleteConversation(conversationId) }
    }

    override suspend fun addUserToConversation(conversationId: String, userId: String) {
        Either.catch { conversationDao.addMember(ConversationMemberEntity(conversationId, userId)) }
    }

    override suspend fun removeUserFromConversation(conversationId: String, userId: String) {
        Either.catch { conversationDao.removeMember(conversationId, userId) }
    }

}