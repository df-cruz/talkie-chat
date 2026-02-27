package com.dfcruz.talkie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.theme.TalkieTheme

@Composable
fun TalkieTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = TalkieTheme.typography.bodyMedium,
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    placeholder: @Composable (RowScope.() -> Unit)? = null,
    leadingContent: @Composable (RowScope.() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null,
) {
    BasicTextField(
        modifier = modifier
            .clip(shape = TalkieTheme.shapes.extraLarge)
            .background(TalkieTheme.colors.surfaceContainerHigh)
            .padding(innerPadding),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = textStyle,
        cursorBrush = SolidColor(TalkieTheme.colors.primary),
        decorationBox = { innerTextField ->
            DecorationBox(
                value.text,
                innerTextField,
                placeholder,
                leadingContent,
                trailingContent,
            )
        },
        maxLines = maxLines,
        singleLine = maxLines == 1,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
    )
}

@Composable
private fun DecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholder: @Composable (RowScope.() -> Unit)? = null,
    leadingContent: @Composable (RowScope.() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.let {
            leadingContent()
            Spacer(Modifier.width(8.dp))
        }

        Box(modifier = Modifier.weight(1f)) {
            innerTextField()
            if (value.isEmpty() && placeholder != null) {
                this@Row.placeholder()
            }
        }

        trailingContent?.let {
            Spacer(Modifier.width(8.dp))
            trailingContent()
        }
    }
}