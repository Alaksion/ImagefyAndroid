package multiplatform.ui.design.scrollcontainer

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
fun HorizontalScrollContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(ImagefySpacing.Small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            content()
        }
        VerticalSpacer(ImagefySpacing.XSmall)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScrollBar(height = ImagefySpacing.XSmall3, scrollState = scrollState)
        )
    }
}

@Composable
private fun Modifier.horizontalScrollBar(
    height: Dp,
    scrollState: ScrollState
): Modifier {
    val color = MaterialTheme.colorScheme
    val scrollProgression = remember {
        derivedStateOf {
            if (scrollState.maxValue > 0) {
                (scrollState.value.toFloat() / scrollState.maxValue.toFloat())
            } else 0f
        }
    }
    val indicator = remember(scrollProgression) {
        drawWithContent {
            val barWidth = size.width * 0.05f

            drawContent()
            drawRoundRect(
                color = color.onSurfaceVariant,
                size = Size(height = height.toPx(), width = barWidth),
                topLeft = Offset(
                    x = (size.width - barWidth) * scrollProgression.value,
                    y = size.height
                )
            )
        }
    }

    return this then indicator
}