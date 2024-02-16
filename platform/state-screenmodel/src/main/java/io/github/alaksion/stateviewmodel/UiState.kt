package io.github.alaksion.stateviewmodel

data class UiState<T>(
    val data: T,
    val mode: UiMode
)
