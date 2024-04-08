package multiplatform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal actual fun rememberDeviceDimensions(): DeviceDimensions {
    val window = LocalWindowInfo.current
    return remember {
        DeviceDimensions(
            height = window.containerSize.height.dp,
            width = window.containerSize.width.dp
        )
    }
}