package com.dfcruz.talkie.ui.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.R
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatScreenViewModel = koinViewModel(),
    onClose: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    ChatScreen(
        modifier = modifier,
        state = uiState.value,
        onClose = onClose,
        onSendMessage = { viewModel.sendMessage(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatScreen(
    modifier: Modifier = Modifier,
    state: ChatScreenUiState,
    onClose: () -> Unit,
    onSendMessage: (String) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0),
        topBar = {
            TopAppBar(
                title = {
                    Text(state.conversationName)
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .navigationBarsPadding()
                .imePadding(),
        ) {
            MessagesList(
                modifier = Modifier.weight(1f),
                messages = state.messages,
            )
            MessageComposer(onSendMessage = onSendMessage)
        }
    }
}

@Composable
private fun MessagesList(
    modifier: Modifier = Modifier,
    messages: List<MessageUiModel>,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        reverseLayout = true,
    ) {
        items(messages, { it.id }) {
            ChatMessage(message = it.message)
        }
    }
}

@Composable
private fun ChatMessage(
    modifier: Modifier = Modifier,
    message: String,
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Text(message)
    }
}

@Composable
@Preview
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen(
            state = ChatScreenUiState(
                conversationName = "Conversation",
                messages = listOf(
                    MessageUiModel("1", "Ping"),
                    MessageUiModel("2", "Pong")
                ).reversed()
            ),
            onClose = {},
            onSendMessage = {},
        )
    }
}
