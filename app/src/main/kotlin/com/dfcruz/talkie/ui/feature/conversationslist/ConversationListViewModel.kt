package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConversationListViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<List<Conversation>> = MutableStateFlow(listOf())
    val uiState: StateFlow<List<Conversation>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.update {
                listOf(
                    Conversation(id = 1, title = "General Chat", subtitle = "Talk about anything"),
                    Conversation(id = 2, title = "Project Alpha", subtitle = "Sprint planning"),
                    Conversation(id = 3, title = "Support", subtitle = "Customer issues"),
                    Conversation(id = 4, title = "Design", subtitle = "UI/UX discussions"),
                    Conversation(id = 5, title = "Engineering", subtitle = "Tech talk"),
                    Conversation(id = 6, title = "Marketing", subtitle = "Campaign ideas"),
                    Conversation(id = 7, title = "HR", subtitle = "People ops"),
                    Conversation(id = 8, title = "Random", subtitle = "Off-topic"),
                    Conversation(id = 9, title = "Announcements", subtitle = "Important updates"),
                    Conversation(id = 10, title = "Archive")
                )
            }
        }
    }

}