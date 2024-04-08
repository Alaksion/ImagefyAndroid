package multiplatform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
internal expect fun rememberDeviceDimensions(): DeviceDimensions

internal data class DeviceDimensions(
    val height: Dp,
    val width: Dp
)