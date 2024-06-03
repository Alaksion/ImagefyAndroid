package multiplatform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

@Composable
fun rememberImageProportionalHeight(
    containerWidth: Dp,
    originalImageWidth: Dp,
    originalImageHeight: Dp
): Dp {
    return remember(containerWidth, originalImageWidth, originalImageHeight) {
        val widthRatio = containerWidth / originalImageWidth
        originalImageHeight * widthRatio
    }
}