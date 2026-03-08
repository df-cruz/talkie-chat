package com.dfcruz.talkie.ui.feature.chat.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.component.TalkieTextField
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview

@Composable
fun MessageComposer(
    modifier: Modifier = Modifier,
    text: String = "",
    onSendMessage: (String) -> Unit,
) {
    var textFieldValue by remember(text) { mutableStateOf(TextFieldValue(text = text)) }
    val isSendVisible = textFieldValue.text.isNotEmpty()

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = TalkieTheme.colors.surfaceContainer
    ) {
        TalkieTextField(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .animateContentSize(),
            value = textFieldValue,
            textStyle = TalkieTheme.typography.bodyMedium,
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(
                        visible = isSendVisible,
                        enter = fadeIn(animationSpec = tween(durationMillis = 150)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 150))
                    ) {
                        SendMessageButton(
                            onClick = {
                                onSendMessage(textFieldValue.text)
                                textFieldValue = TextFieldValue()
                            }
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun SendMessageButton(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .size(36.dp)
            .clip(CircleShape)
            .background(TalkieTheme.colors.primary)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = ripple(bounded = true)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .rotate(90f),
            painter = painterResource(R.drawable.arrow_back),
            contentDescription = stringResource(R.string.send_message),
            tint = TalkieTheme.colors.inverseOnSurface
        )
    }
}

@Composable
@ThemePreview
fun MessageComposerEmptyPreview() {
    TalkieTheme {
        MessageComposer(
            text = "",
            onSendMessage = {}
        )
    }
}

@Composable
@ThemePreview
fun MessageComposerWithTextPreview() {
    TalkieTheme {
        MessageComposer(
            text = "Hey, how are you doing?",
            onSendMessage = {}
        )
    }
}