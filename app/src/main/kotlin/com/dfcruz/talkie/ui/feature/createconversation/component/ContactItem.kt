package com.dfcruz.talkie.ui.feature.createconversation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dfcruz.talkie.ui.component.Avatar
import com.dfcruz.talkie.ui.component.AvatarSize
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview

@Composable
fun ContactItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            name = name,
            size = AvatarSize.Medium,
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = name,
            style = TalkieTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@ThemePreview
private fun ContactItemPreview() {
    TalkieTheme {
        ContactItem(name = "John Doe") { }
    }
}