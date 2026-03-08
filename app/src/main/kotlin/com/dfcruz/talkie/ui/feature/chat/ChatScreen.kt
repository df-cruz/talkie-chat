package com.dfcruz.talkie.ui.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.ui.feature.chat.component.ChatMessageList
import com.dfcruz.talkie.ui.feature.chat.component.ChatTopBar
import com.dfcruz.talkie.ui.feature.chat.component.MessageComposer
import com.dfcruz.talkie.ui.theme.TalkieTheme
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

@Composable
private fun ChatScreen(
    modifier: Modifier = Modifier,
    state: ChatScreenUiState,
    onClose: () -> Unit = {},
    onSendMessage: (String) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0),
        topBar = {
            ChatTopBar(title = state.conversationName, onBackPressed = onClose)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .navigationBarsPadding()
                .imePadding(),
        ) {
            ChatMessageList(
                modifier = Modifier.weight(1f),
                messages = state.messages,
            )
            MessageComposer(onSendMessage = onSendMessage)
        }
    }
}

@Composable
@Preview
fun ChatScreenPreview() {
    TalkieTheme {
        ChatScreen(
            state = ChatScreenUiState(
                conversationName = "Chat",
                messages = listOf()
            ),
        )
    }
}
