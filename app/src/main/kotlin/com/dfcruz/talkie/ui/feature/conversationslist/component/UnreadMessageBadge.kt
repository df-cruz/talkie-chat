package com.dfcruz.talkie.ui.feature.conversationslist.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview

@Composable
fun UnreadMessageBadge(
    modifier: Modifier = Modifier,
    number: Int,
) {
    Surface(
        modifier = modifier,
        color = TalkieTheme.colors.primary,
        shape = CircleShape,
    ) {

        Text(
            text = "$number",
            maxLines = 1,
            color = TalkieTheme.colors.onPrimary,
        )
    }
}

@ThemePreview
@Composable
fun UnreadMessageBadgePreview() {
    TalkieTheme() {
        UnreadMessageBadge(number = 1)
    }
}