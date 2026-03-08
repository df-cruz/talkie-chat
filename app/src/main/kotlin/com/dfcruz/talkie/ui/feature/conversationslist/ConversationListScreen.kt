package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.ui.feature.conversationslist.component.ConversationsList
import com.dfcruz.talkie.ui.feature.conversationslist.component.ConversationsTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationListScreen(
    modifier: Modifier = Modifier,
    viewModel: ConversationListViewModel = koinViewModel(),
    onClickConversation: (String) -> Unit,
    onCreateConversation: () -> Unit,
) {
    val conversations = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = { ConversationsTopAppBar(onCreateConversation = onCreateConversation) }
    ) { paddingValues ->
        ConversationsList(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            conversations = conversations.value,
            onClickConversation = onClickConversation,
            onLongClickConversation = { }
        )
    }
}