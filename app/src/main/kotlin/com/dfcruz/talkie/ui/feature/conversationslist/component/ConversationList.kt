package com.dfcruz.talkie.ui.feature.conversationslist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.feature.conversationslist.model.ConversationSubtitle
import com.dfcruz.talkie.ui.feature.conversationslist.model.ConversationUiModel
import com.dfcruz.talkie.ui.feature.conversationslist.model.UnreadState
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun ConversationsList(
    modifier: Modifier = Modifier,
    conversations: List<ConversationUiModel>,
    onClickConversation: (String) -> Unit = {},
    onLongClickConversation: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Conversations",
                style = TalkieTheme.typography.headlineLarge,
            )
        }

        items(conversations, { it.id }) {
            ConversationItem(
                conversation = it,
                onClick = { onClickConversation(it.id) },
                onLongClick = { onLongClickConversation(it.id) }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ConversationsListPreview() {
    TalkieTheme {
        Box(
            modifier = Modifier
        ) {
            ConversationsList(
                modifier = Modifier.fillMaxSize(),
                conversations = listOf(
                    ConversationUiModel(
                        id = "1",
                        title = "John Doe",
                        subtitle = ConversationSubtitle.Typing,
                        lastMessageTime = "10:00",
                        unreadState = UnreadState.Count(1)
                    ),
                    ConversationUiModel(
                        id = "2",
                        title = "Alice",
                        subtitle = ConversationSubtitle.LastMessage("How are you?"),
                        lastMessageTime = "10:00",
                        unreadState = UnreadState.None
                    ),
                ),
            )
        }
    }
}