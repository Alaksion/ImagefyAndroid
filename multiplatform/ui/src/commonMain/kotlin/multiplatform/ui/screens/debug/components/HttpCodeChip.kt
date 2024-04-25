package multiplatform.ui.screens.debug.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun HttpCodeChip(
    code: Int,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    val borderColor = remember(code) {
        when (code) {
            in 200..299 -> colorScheme.primary
            in 400..499 -> colorScheme.secondary
            in 500..599 -> colorScheme.error
            else -> Color.Black
        }

    }

    Text(text = code.toString(), color = borderColor)
}