package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.feature.chat.model.MessageAuthor
import com.dfcruz.talkie.ui.feature.chat.model.MessageGroupPosition
import com.dfcruz.talkie.ui.theme.TalkieTheme

object ChatBubblePolicy {

    fun spacing(position: MessageGroupPosition): Dp {
        return when (position) {
            MessageGroupPosition.First -> 4.dp
            is MessageGroupPosition.Middle -> 4.dp
            is MessageGroupPosition.Last -> 16.dp // The list of messages is displayed upside-down
        }
    }

    @Composable
    fun bubbleShape(author: MessageAuthor, position: MessageGroupPosition): Shape {
        return when (author) {
            MessageAuthor.CurrentUser -> when (position) {
                MessageGroupPosition.First -> TalkieTheme.shapes.bubbleFullRounded
                is MessageGroupPosition.Middle -> TalkieTheme.shapes.large
                is MessageGroupPosition.Last -> TalkieTheme.shapes.bubbleBottomRight
            }

            is MessageAuthor.External -> when (position) {
                MessageGroupPosition.First -> TalkieTheme.shapes.bubbleFullRounded
                is MessageGroupPosition.Middle -> TalkieTheme.shapes.bubbleFullRounded
                is MessageGroupPosition.Last -> TalkieTheme.shapes.bubbleBottomLeft
            }
        }
    }

    @Composable
    fun bubbleColor(author: MessageAuthor): Color = when (author) {
        MessageAuthor.CurrentUser -> TalkieTheme.colors.primary
        is MessageAuthor.External -> TalkieTheme.colors.surfaceContainerHighest
    }

    @Composable
    fun bubbleTextColor(author: MessageAuthor): Color = when (author) {
        is MessageAuthor.CurrentUser -> TalkieTheme.colors.onPrimary
        is MessageAuthor.External -> TalkieTheme.colors.onSurface
    }

    fun messageArrangement(author: MessageAuthor): Arrangement.Horizontal = when (author) {
        MessageAuthor.CurrentUser -> Arrangement.End
        is MessageAuthor.External -> Arrangement.Start
    }

    fun bubbleAlignment(author: MessageAuthor): Alignment.Horizontal = when (author) {
        is MessageAuthor.CurrentUser -> Alignment.End
        is MessageAuthor.External -> Alignment.Start
    }
}
