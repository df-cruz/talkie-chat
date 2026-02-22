package com.dfcruz.talkie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.dfcruz.talkie.ui.theme.talkie.TalkieDarkColorScheme
import com.dfcruz.talkie.ui.theme.talkie.TalkieLightColorScheme
import com.dfcruz.talkie.ui.theme.talkie.TalkieTypography

@Composable
fun TalkieTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) TalkieDarkColorScheme else TalkieLightColorScheme

    CompositionLocalProvider(
        LocalThemeColorScheme provides colorScheme,
        LocalThemeTypography provides TalkieTypography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme.toMaterialColorScheme(),
            typography = TalkieTypography.toMaterialTypography(),
            content = content,
        )
    }
}

object TalkieTheme {

    val colors: ThemeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeColorScheme.current

    val typography: ThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeTypography.current
}
