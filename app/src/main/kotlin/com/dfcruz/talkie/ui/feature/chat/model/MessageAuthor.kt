package com.dfcruz.talkie.ui.feature.chat.model

sealed class MessageAuthor {
    data object CurrentUser : MessageAuthor()
    data class External(val details: Author) : MessageAuthor()
}