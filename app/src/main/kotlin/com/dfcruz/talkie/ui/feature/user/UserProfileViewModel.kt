package com.dfcruz.talkie.ui.feature.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserProfileViewModel(
    userId: String,
    userRepository: UserRepository,
) : ViewModel() {

    val profileDetails: StateFlow<UserProfileUiState> = userRepository.getUserFlow(userId)
        .map {
            UserProfileUiState(
                displayName = it.name,
                avatarUrl = it.avatarUrl,
                phone = it.phoneNumber,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = UserProfileUiState()
        )

}