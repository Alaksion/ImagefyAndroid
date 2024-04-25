package multiplatform.ui.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
internal fun BaseCard(
    modifier: Modifier = Modifier,
    spacing: Dp = ImagefySpacing.Small,
    leading: (@Composable RowScope.() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit,

    ) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        leading?.let { it() }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            content()
        }
        trailing?.let { it() }
    }
}