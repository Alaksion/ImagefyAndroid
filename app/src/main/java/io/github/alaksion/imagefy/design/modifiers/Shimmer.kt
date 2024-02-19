package io.github.alaksion.imagefy.design.modifiers

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmer(display: Boolean): Modifier {
    return composed {
        val shimmerColors = shimmerColors()
        if (display) {
            this.background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = 100f, y = 0.0f),
                    end = Offset(x = 400f, y = 270f),
                ),
            )
        } else this
    }
}

@Composable
private fun shimmerColors(): List<Color> {
    return listOf(
        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.background.copy(alpha = 1.0f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
    )
}