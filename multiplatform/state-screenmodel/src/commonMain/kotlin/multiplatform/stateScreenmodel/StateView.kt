package multiplatform.stateScreenmodel

import androidx.compose.runtime.Composable

@Composable
fun <T> UiState<T>.View(
    contentView: @Composable () -> Unit,
    errorView: @Composable Throwable.() -> Unit,
    loadingView: @Composable () -> Unit
) {
    when (this.mode) {
        UiMode.Content -> contentView()
        UiMode.Loading -> loadingView()
        is UiMode.Error -> this.mode.error.errorView()
    }
}