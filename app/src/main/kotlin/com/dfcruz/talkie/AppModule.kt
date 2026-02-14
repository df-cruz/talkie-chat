package com.dfcruz.talkie

import com.dfcruz.talkie.ui.feature.chat.ChatScreenViewModel
import com.dfcruz.talkie.ui.feature.conversationslist.ConversationListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::ConversationListViewModel)
    viewModelOf(::ChatScreenViewModel)
}