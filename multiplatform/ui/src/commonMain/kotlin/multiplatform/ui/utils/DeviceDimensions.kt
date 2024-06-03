package multiplatform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
expect fun rememberDeviceDimensions(): DeviceDimensions

data class DeviceDimensions(
    val height: Dp,
    val width: Dp
)