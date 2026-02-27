package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.component.TalkieTextField
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun MessageComposer(
    modifier: Modifier = Modifier,
    text: String = "",
    onSendMessage: (String) -> Unit,
) {
    var textFieldValue by remember(text) { mutableStateOf(TextFieldValue(text = text)) }

    Surface(
        modifier = modifier

    ) {
        val textStyle = TalkieTheme.typography.bodyMedium
        TalkieTextField(
            modifier = modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .animateContentSize()
                .fillMaxWidth()
                .animateContentSize(),
            value = textFieldValue,
            textStyle = textStyle,
            innerPadding = PaddingValues(
                start = 16.dp,
                top = 6.dp,
                end = 6.dp,
                bottom = 6.dp,
            ),
            onValueChange = { textFieldValue = it },
            trailingContent = {
                Row(
                    modifier = Modifier.height(36.dp),
                ) {
                    AnimatedVisibility(
                        visible = !textFieldValue.text.isEmpty(),
                        enter = fadeIn(animationSpec = tween(durationMillis = 150)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 150))
                    ) {
                        SendMessageButton {
                            onSendMessage(textFieldValue.text)
                            textFieldValue = textFieldValue.copy("")
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun SendMessageButton(onClick: () -> Unit) {
    Box(
        Modifier
            .clip(CircleShape)
            .background(TalkieTheme.colors.inverseSurface)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp)
                .rotate(90f),
            painter = painterResource(R.drawable.arrow_back),
            contentDescription = null,
            tint = TalkieTheme.colors.inverseOnSurface
        )
    }
}

@Preview
@Composable
fun MessageComposerPreview() {
    var value by remember { mutableStateOf("") }
    TalkieTheme {
        Column() {
            MessageComposer(text = value) {}
            MessageComposer(text = "3") {}
        }
    }
}