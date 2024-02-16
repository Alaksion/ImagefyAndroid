package io.github.alaksion.stateviewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class StateUpdater<T>(
    private val flow: MutableStateFlow<UiState<T>>
) {

    fun update(
        block: (T) -> T
    ) {
        flow.update { UiState(data = block(flow.value.data), mode = flow.value.mode) }
    }

}