package com.dfcruz.talkie.ui.feature.user

data class UserProfileUiState(
    val displayName: String = "",
    val avatarUrl: String? = null,
    val phone: String? = null,
)