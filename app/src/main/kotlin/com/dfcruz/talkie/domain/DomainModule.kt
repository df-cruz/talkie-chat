package com.dfcruz.talkie.domain

import com.dfcruz.talkie.domain.usecase.CreateConversationUseCase
import com.dfcruz.talkie.domain.usecase.GetCurrentUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateConversationUseCase(get(), get(), get()) }
    factory { GetCurrentUserUseCase(get()) }
}