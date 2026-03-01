package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.repository.ConversationRepository
import com.dfcruz.talkie.ui.feature.conversationslist.model.ConversationUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ConversationListViewModel(
    conversationRepository: ConversationRepository,
) : ViewModel() {

    val uiState: StateFlow<List<ConversationUiModel>> =
        conversationRepository.getConversationsFlow()
            .map {
                it.map { conversation ->
                    ConversationUiModel(
                        id = conversation.id,
                        title = conversation.name.orEmpty(),
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = listOf()
            )

}