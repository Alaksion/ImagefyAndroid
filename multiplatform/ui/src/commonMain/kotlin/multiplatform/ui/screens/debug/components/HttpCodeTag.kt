package multiplatform.ui.screens.debug.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
internal fun HttpCodeTag(
    code: Int,
    modifier: Modifier = Modifier
) {
    val borderColor = remember(code) {
        when (code) {
            in 100..199 -> Color(0xff31708F)
            in 200..299 -> Color(0xff4F8A10)
            in 300..399 -> Color(0xffD81B60)
            in 400..499 -> Color(0xff9F6000)
            in 500..599 -> Color(0xffD8000C)
            else -> Color.Black
        }
    }
    val backgroundColor = remember(code) {
        when (code) {
            in 100..199 -> Color(0xffD9EDF7)
            in 200..299 -> Color(0xffDFF2BF)
            in 300..399 -> Color(0xffF8BBD0)
            in 400..499 -> Color(0xffFEEFB3)
            in 500..599 -> Color(0xffFFBABA)
            else -> Color.Black
        }
    }

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .border(width = 1.dp, color = borderColor, shape = MaterialTheme.shapes.extraSmall)
            .background(backgroundColor)
            .padding(horizontal = ImagefySpacing.XSmall2, vertical = ImagefySpacing.XSmall3)
    ) {
        Text(
            text = code.toString(),
            color = borderColor
        )
    }

}