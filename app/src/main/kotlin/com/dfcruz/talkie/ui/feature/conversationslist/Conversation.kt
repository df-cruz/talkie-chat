package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.compose.runtime.Immutable

@Immutable
data class ConversationUiModel(
    val id: Int,
    val title: String? = null,
    val subtitle: String? = null
)