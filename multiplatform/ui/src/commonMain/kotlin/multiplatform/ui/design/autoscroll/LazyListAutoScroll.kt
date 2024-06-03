package multiplatform.ui.design.autoscroll

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LazyListState.AutoScroll(
    enabled: Boolean = true,
    offset: Int = 0,
    onNext: () -> Unit,
) {

    // Should be done with derivedState but it's not working
    LaunchedEffect(layoutInfo) {
        val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
        lastItem?.let {
            if (it.index >= layoutInfo.totalItemsCount - offset && enabled) {
                onNext()
            }
        }
    }
}