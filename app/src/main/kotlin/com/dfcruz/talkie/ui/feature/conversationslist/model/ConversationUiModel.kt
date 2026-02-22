package com.dfcruz.talkie.ui.feature.conversationslist.model

import androidx.compose.runtime.Immutable

@Immutable
data class ConversationUiModel(
    val id: String,
    val title: String,
    val subtitle: ConversationSubtitle? = null,
    val lastMessageTime: String? = null,
    val unreadState: UnreadState = UnreadState.None,
)