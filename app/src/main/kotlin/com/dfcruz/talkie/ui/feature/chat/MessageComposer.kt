package com.dfcruz.talkie.ui.feature.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.R

@Composable
fun MessageComposer(
    modifier: Modifier = Modifier,
    onSendMessage: (String) -> Unit,
) {
    val textField = rememberTextFieldState()
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.padding(16.dp),
            state = textField,
            trailingIcon = {

                AnimatedVisibility(
                    visible = textField.text.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    IconButton(onClick = {
                        onSendMessage(textField.text.toString())
                        textField.clearText()
                    }) {
                        Icon(
                            modifier = Modifier.rotate(90f),
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                }

            }
        )
    }
}


@Preview
@Composable
fun MessageComposerPreview() {
    MaterialTheme {
        MessageComposer {}
    }
}