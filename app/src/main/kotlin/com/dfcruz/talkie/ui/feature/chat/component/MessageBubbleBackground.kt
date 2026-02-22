package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun MessageBubbleBackground(
    modifier: Modifier = Modifier,
    color: Color,
    shape: Shape,
    content: @Composable () -> Unit = {},
) = Surface(
    modifier = modifier,
    shape = shape,
    color = color,
    contentColor = contentColorFor(color),
    border = null
) {
    content()
}
