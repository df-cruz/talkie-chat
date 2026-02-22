package com.dfcruz.talkie.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class ThemeShapes(
    val avatar: CornerBasedShape,

    // Material shapes
    val extraSmall: CornerBasedShape,
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
    val extraLarge: CornerBasedShape,

    val bubbleFullRounded: CornerBasedShape,
    val bubbleBottomLeft: CornerBasedShape,
    val bubbleBottomRight: CornerBasedShape,
)

internal fun ThemeShapes.toMaterialShapes() = Shapes(
    extraSmall = extraSmall,
    small = small,
    medium = medium,
    large = large,
    extraLarge = extraLarge,
)

internal val LocalThemeShapes = staticCompositionLocalOf<ThemeShapes> {
    error("No ThemeShapes provided")
}