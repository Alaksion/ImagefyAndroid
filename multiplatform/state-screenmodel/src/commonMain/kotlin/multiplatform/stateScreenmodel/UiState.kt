package multiplatform.stateScreenmodel

data class UiState<T>(
    val data: T,
    val mode: UiMode
)
