package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.feature.chat.model.Author
import com.dfcruz.talkie.ui.feature.chat.model.MessageAuthor
import com.dfcruz.talkie.ui.feature.chat.model.MessageContent
import com.dfcruz.talkie.ui.feature.chat.model.MessageGroupPosition
import com.dfcruz.talkie.ui.feature.chat.model.MessageUiModel
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview

@Composable
fun ChatMessageList(
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
@ThemePreview
private fun ChatMessageListPreview() {
    TalkieTheme {
        ChatMessageList(
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
        )
    }

}