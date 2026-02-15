package com.dfcruz.talkie

import com.dfcruz.talkie.system.ContactsProvider
import com.dfcruz.talkie.ui.feature.chat.ChatScreenViewModel
import com.dfcruz.talkie.ui.feature.conversationslist.ConversationListViewModel
import com.dfcruz.talkie.ui.feature.createconversation.CreateConversationViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ConversationListViewModel)
    viewModelOf(::ChatScreenViewModel)
    viewModelOf(::CreateConversationViewModel)

    single { ContactsProvider(androidApplication()) }
}