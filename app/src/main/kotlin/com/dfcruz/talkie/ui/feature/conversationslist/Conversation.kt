package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.compose.runtime.Immutable

@Immutable
data class Conversation(
    val id: Int,
    val title: String? = null,
    val subtitle: String? = null
)