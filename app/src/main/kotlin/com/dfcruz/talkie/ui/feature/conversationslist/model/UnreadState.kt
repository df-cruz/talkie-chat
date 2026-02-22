package com.dfcruz.talkie.ui.feature.conversationslist.model

sealed interface UnreadState {
    data object None : UnreadState
    data class Count(val count: Int) : UnreadState
    data object Muted : UnreadState  // future-proof
}