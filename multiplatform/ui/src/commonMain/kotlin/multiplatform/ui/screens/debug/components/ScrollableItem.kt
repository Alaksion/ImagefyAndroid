package multiplatform.ui.screens.debug.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.scrollcontainer.HorizontalScrollContainer
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
internal fun DebugScrollableItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        VerticalSpacer(ImagefySpacing.XSmall)
        HorizontalScrollContainer(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = content,
            )
        }
    }
}