package com.dfcruz.talkie.data.repository

import com.dfcruz.talkie.domain.repository.ConversationRepository
import com.dfcruz.talkie.domain.repository.MessageRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ConversationRepository> {
        ConversationRepositoryImpl(
            conversationDao = get(),
        )
    }
    single<MessageRepository> {
        MessageRepositoryImpl(
            messageDao = get(),
        )
    }
}