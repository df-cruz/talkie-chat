package com.dfcruz.talkie.ui.feature.conversationslist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.feature.conversationslist.model.ConversationSubtitle
import com.dfcruz.talkie.ui.feature.conversationslist.model.ConversationUiModel
import com.dfcruz.talkie.ui.feature.conversationslist.model.UnreadState
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview

@Composable
fun ConversationItem(
    modifier: Modifier = Modifier,
    conversation: ConversationUiModel,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        ConversationItemContent(
            modifier = Modifier.weight(1f),
            title = conversation.title,
            subtitle = conversation.subtitle,
        )

        Spacer(modifier = Modifier.width(8.dp))

        ConversationItemMeta(
            time = conversation.lastMessageTime,
            unreadState = conversation.unreadState,
        )
    }
}

@Composable
private fun ConversationItemContent(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: ConversationSubtitle?,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            style = TalkieTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        ConversationItemSubtitle(subtitle = subtitle)
    }
}

@Composable
private fun ConversationItemSubtitle(
    subtitle: ConversationSubtitle?,
) {
    when (subtitle) {
        is ConversationSubtitle.LastMessage -> Text(
            text = subtitle.text,
            style = TalkieTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        ConversationSubtitle.Typing -> Text(
            text = stringResource(R.string.typing_conversation_label),
            style = TalkieTheme.typography.bodyMedium,
            color = TalkieTheme.colors.primary,
            maxLines = 1,
        )
        null -> Unit
    }
}

@Composable
private fun ConversationItemMeta(
    modifier: Modifier = Modifier,
    time: String?,
    unreadState: UnreadState,
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = time.orEmpty(),
            style = TalkieTheme.typography.bodyMedium,
            maxLines = 1,
        )

        Spacer(modifier = Modifier.height(4.dp))

        if (unreadState is UnreadState.Count) {
            UnreadMessageIndicator(unreadState.count)
        }
    }
}

@Composable
private fun UnreadMessageIndicator(
    unreadCount: Int,
) {
    Box(
        modifier = Modifier
            .size(18.dp)
            .background(
                color = TalkieTheme.colors.primaryContainer,
                shape = TalkieTheme.shapes.avatar
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            color = TalkieTheme.colors.onPrimaryContainer,
            text = unreadCount.toString(),
            style = TalkieTheme.typography.labelSmall,
        )
    }
}

@ThemePreview
@Composable
private fun ConversationItemPreview() {
    TalkieTheme {
        ConversationItem(
            conversation = ConversationUiModel(
                id = "1",
                title = "Alice Johnson",
                subtitle = ConversationSubtitle.LastMessage("Hey, are we still on for tomorrow?"),
                lastMessageTime = "09:15",
                unreadState = UnreadState.Count(1),
            )
        )
    }
}