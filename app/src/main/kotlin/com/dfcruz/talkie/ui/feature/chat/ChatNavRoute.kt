package com.dfcruz.talkie.ui.feature.chat

import com.dfcruz.talkie.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data class ChatNavRoute(
    val conversationId: String,
) : NavRoute