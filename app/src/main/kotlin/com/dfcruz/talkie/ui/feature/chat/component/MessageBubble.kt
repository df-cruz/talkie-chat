package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    color: Color,
    shape: Shape,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    MessageBubbleBackground(
        modifier = modifier,
        color = color,
        shape = shape,
    ) {
        Column(
            modifier = Modifier
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick
                )
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp
                )
        ) {
            content()
        }
    }
}
