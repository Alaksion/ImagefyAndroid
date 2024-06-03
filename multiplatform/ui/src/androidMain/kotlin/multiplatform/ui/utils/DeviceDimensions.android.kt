package multiplatform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
actual fun rememberDeviceDimensions(): DeviceDimensions {
    val config = LocalConfiguration.current
    return remember {
        DeviceDimensions(
            height = config.screenHeightDp.dp,
            width = config.screenWidthDp.dp
        )
    }
}