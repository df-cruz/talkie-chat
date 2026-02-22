package com.dfcruz.talkie.ui.feature.chat.model

sealed class MessageGroupPosition(open val index: Int) {
    data object First : MessageGroupPosition(0)
    data class Middle(override val index: Int) : MessageGroupPosition(index)
    data class Last(override val index: Int) : MessageGroupPosition(index)
}