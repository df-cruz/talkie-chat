package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.model.Conversation
import com.dfcruz.talkie.domain.model.User
import com.dfcruz.talkie.domain.repository.ConversationRepository
import com.dfcruz.talkie.domain.repository.UserRepository
import java.util.UUID
import kotlin.time.Clock

class CreateConversationUseCase(
    private val conversationRepository: ConversationRepository,
    private val userRepository: UserRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {

    suspend operator fun invoke(
        contactName: String,
        contact: String,
    ): Conversation? {

        val currentUser = getCurrentUserUseCase()
        val directUser = userRepository.getUserByContact(contact)
            ?: userRepository.addUser(
                User(
                    id = UUID.randomUUID().toString(),
                    name = contactName,
                    phoneNumber = contact,
                    createdAt = Clock.System.now()
                )
            )

        val conversationId = generateDirectKey(currentUser.id, directUser.id)
        val conversation = conversationRepository.getConversation(conversationId)

        return conversation
            ?: conversationRepository.createConversation(
                Conversation(
                    id = conversationId,
                    name = contactName,
                    ownerId = currentUser.id,
                    participants = listOf(currentUser, directUser),
                    createdAt = Clock.System.now()
                )
            )
    }

    private fun generateDirectKey(ownerId: String, toUserId: String): String {
        return listOf(ownerId, toUserId)
            .sorted()
            .joinToString("_")
    }
}