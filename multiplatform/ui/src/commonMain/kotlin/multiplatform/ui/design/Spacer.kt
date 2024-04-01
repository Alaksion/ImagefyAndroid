package multiplatform.ui.design

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Spacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun RowScope.Spacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun VerticalSpacer(size: Dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun HorizontalSpacer(size: Dp) {
    Spacer(modifier = Modifier.width(size))
}