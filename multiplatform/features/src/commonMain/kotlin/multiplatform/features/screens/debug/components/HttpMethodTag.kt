package multiplatform.features.screens.debug.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
internal fun HttpMethodTag(
    method: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = MaterialTheme.shapes.extraSmall
            )
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = ImagefySpacing.XSmall2, vertical = ImagefySpacing.XSmall3)
    ) {
        Text(
            text = method,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}