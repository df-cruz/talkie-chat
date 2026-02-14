package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ConversationListScreen(
    modifier: Modifier = Modifier,
    viewModel: ConversationListViewModel = koinViewModel()
) {
    val conversations = viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(conversations.value, { it.id }) {
            ConversationItem(conversation = it)
        }
    }
}

@Composable
fun ConversationItem(
    modifier: Modifier = Modifier,
    conversation: ConversationUiModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(conversation.title.orEmpty())
        Text(conversation.subtitle.orEmpty())
    }
}