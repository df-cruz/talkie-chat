package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dfcruz.talkie.ui.feature.chat.model.MessageContent
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun MessageContent(
    modifier: Modifier = Modifier,
    content: MessageContent,
    contentColor: Color,
) {
    when (content) {
        is MessageContent.Text -> MessageTextContent(
            modifier = modifier,
            content = content,
            contentColor = contentColor,
        )
    }
}

@Composable
private fun MessageTextContent(
    modifier: Modifier = Modifier,
    content: MessageContent.Text,
    contentColor: Color,
) {
    Text(
        modifier = modifier,
        text = content.text,
        style = TalkieTheme.typography.bodyMedium,
        color = contentColor,
    )
}