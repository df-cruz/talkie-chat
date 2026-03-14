package com.dfcruz.talkie.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String? = null,
    size: AvatarSize = AvatarSize.Medium,
) {
    val initial = remember(name) { name.firstOrNull()?.uppercaseChar()?.toString().orEmpty() }

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(TalkieTheme.colors.primary),
        contentAlignment = Alignment.Center,
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Text(
                text = initial,
                style = size.textStyle(),
                color = Color.White,
            )
        }
    }
}

enum class AvatarSize(val dp: Dp) {
    Small(28.dp),
    Medium(40.dp),
    Large(56.dp),
    ExtraLarge(96.dp);

    @Composable
    fun textStyle(): TextStyle = when (this) {
        Small -> TalkieTheme.typography.labelMedium
        Medium -> TalkieTheme.typography.titleMedium
        Large -> TalkieTheme.typography.headlineSmall
        ExtraLarge -> TalkieTheme.typography.headlineMedium
    }
}

@ThemePreview
@Composable
private fun AvatarInitialPreview() {
    TalkieTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            Avatar(name = "Alice Johnson", size = AvatarSize.Small)
            Avatar(name = "Mia Chen", size = AvatarSize.Medium)
            Avatar(name = "Bob Smith", size = AvatarSize.Large)
            Avatar(name = "Fade Smith", size = AvatarSize.ExtraLarge)
        }
    }
}