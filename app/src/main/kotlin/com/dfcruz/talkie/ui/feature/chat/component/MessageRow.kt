package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.util.compose.ThemePreview
import com.dfcruz.talkie.ui.feature.chat.model.Author
import com.dfcruz.talkie.ui.feature.chat.model.MessageAuthor
import com.dfcruz.talkie.ui.feature.chat.model.MessageContent
import com.dfcruz.talkie.ui.feature.chat.model.MessageGroupPosition
import com.dfcruz.talkie.ui.feature.chat.model.MessageUiModel
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun MessageRow(
    modifier: Modifier = Modifier,
    message: MessageUiModel,
    onMessagedClick: (String) -> Unit = {},
    onMessagedLongClick: (String) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = ChatBubblePolicy.messageArrangement(message.author)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalAlignment = ChatBubblePolicy.bubbleAlignment(message.author),
        ) {
            MessageBubble(
                modifier = Modifier.wrapContentWidth(),
                color = ChatBubblePolicy.bubbleColor(message.author),
                shape = ChatBubblePolicy.bubbleShape(message.author, message.groupPosition),
                onClick = { onMessagedClick(message.id) },
                onLongClick = { onMessagedLongClick(message.id) },
            ) {
                MessageContent(
                    content = message.content,
                    contentColor = ChatBubblePolicy.bubbleTextColor(message.author)
                )
            }
            Spacer(Modifier.height(4.dp))
            MessageDeliveryTime(time = message.createdAt)
        }
    }
}

@Composable
fun MessageDeliveryTime(
    modifier: Modifier = Modifier,
    time: String
) {
    Text(
        modifier = modifier,
        text = time,
        style = TalkieTheme.typography.labelSmall,
        textAlign = TextAlign.Start
    )
}


@ThemePreview
@Composable
private fun MessageRowPreview() {
    TalkieTheme {
        Column {
            MessageRow(
                message = MessageUiModel(
                    id = "1",
                    content = MessageContent.Text("Morning! Are you coming to the team lunch? 🍜"),
                    createdAt = "11:02",
                    author = MessageAuthor.External(Author(id = "u1", name = "Mia")),
                    groupPosition = MessageGroupPosition.Last(0),
                )
            )
            MessageRow(
                message = MessageUiModel(
                    id = "2",
                    content = MessageContent.Text("Yes! Just need to wrap up a PR first"),
                    createdAt = "11:05",
                    author = MessageAuthor.CurrentUser,
                    groupPosition = MessageGroupPosition.Last(0),
                )
            )
            MessageRow(
                message = MessageUiModel(
                    id = "3",
                    content = MessageContent.Text("No rush, we're booking at 12:30"),
                    createdAt = "11:06",
                    author = MessageAuthor.External(Author(id = "u1", name = "Mia")),
                    groupPosition = MessageGroupPosition.Last(0),
                )
            )
            MessageRow(
                message = MessageUiModel(
                    id = "4",
                    content = MessageContent.Text("Perfect, I'll be there 👌"),
                    createdAt = "11:08",
                    author = MessageAuthor.CurrentUser,
                    groupPosition = MessageGroupPosition.Last(2),
                )
            )
        }
    }
}
