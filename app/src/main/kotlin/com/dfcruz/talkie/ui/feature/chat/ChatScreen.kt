package com.dfcruz.talkie.ui.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.feature.chat.component.MessageComposer
import com.dfcruz.talkie.ui.feature.chat.component.MessageRow
import com.dfcruz.talkie.ui.feature.chat.model.Author
import com.dfcruz.talkie.ui.feature.chat.model.MessageAuthor
import com.dfcruz.talkie.ui.feature.chat.model.MessageContent
import com.dfcruz.talkie.ui.feature.chat.model.MessageGroupPosition
import com.dfcruz.talkie.ui.feature.chat.model.MessageUiModel
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatScreen(
    modifier: Modifier = Modifier,
    state: ChatScreenUiState,
    onClose: () -> Unit,
    onSendMessage: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
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
            modifier = Modifier
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
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        // TODO: temporary scroll
        listState.animateScrollToItem(0)
    }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxWidth(),
        reverseLayout = true,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(messages, { it.id }) { message ->
            MessageRow(Modifier.animateItem(), message = message)
        }
    }
}

@Composable
@Preview
fun ChatScreenPreview() {
    TalkieTheme {
        ChatScreen(
            state = ChatScreenUiState(
                conversationName = "Conversation",
                messages = listOf(
                    MessageUiModel(
                        id = "1",
                        content = MessageContent.Text("Morning! Are you coming to the team lunch? 🍜"),
                        createdAt = "11:02",
                        author = MessageAuthor.External(Author(id = "u1", name = "Mia")),
                        groupPosition = MessageGroupPosition.Last(0),
                    ),
                    MessageUiModel(
                        id = "2",
                        content = MessageContent.Text("Yes! Just need to wrap up a PR first"),
                        createdAt = "11:05",
                        author = MessageAuthor.CurrentUser,
                        groupPosition = MessageGroupPosition.Last(0),

                        )
                ).reversed()
            ),
            onClose = {},
            onSendMessage = {},
        )
    }
}
