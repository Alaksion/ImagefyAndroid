package multiplatform.ui.design.autoscroll

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
internal fun LazyGridState.AutoScroll(
    enabled: Boolean = true,
    offset: Int = 0,
    onNext: () -> Unit,
) {
    val shouldReload = remember(enabled) {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItem != 0
                    && lastVisibleItem == (layoutInfo.totalItemsCount - 1) - offset
                    && enabled
        }
    }
    LaunchedEffect(shouldReload) {
        if (shouldReload.value) onNext()
    }
}