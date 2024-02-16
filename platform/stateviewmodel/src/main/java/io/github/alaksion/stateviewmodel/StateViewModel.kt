package io.github.alaksion.stateviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StateViewModel<T>(
    private val dispatcher: CoroutineDispatcher,
    private val logger: StateLogger = MutedLogger,
    initialState: T,
    initialMode: UiMode = UiMode.Content
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<T>>(
        UiState(
            data = initialState,
            mode = initialMode
        )
    )

    val state: StateFlow<UiState<T>> = _state

    private val stateUpdater = StateUpdater(_state)

    fun setState(
        block: suspend (T) -> T,
        showLoading: Boolean
    ) = updateState(
        block = {
            val result = block(_state.value.data)
            update { result }
        },
        showLoading = showLoading
    )

    fun updateState(
        block: suspend StateUpdater<T>.() -> Unit,
        showLoading: Boolean
    ) {
        viewModelScope.launch(dispatcher) {
            if (showLoading)
                _state.update { old -> old.copy(mode = UiMode.Loading) }
            runCatching {
                block(stateUpdater)
            }.fold(
                onSuccess = {
                    _state.update { old -> old.copy(mode = UiMode.Content) }
                },
                onFailure = { error ->
                    logger.log(error.message.orEmpty(), type = LogType.Error)
                    _state.update { old -> old.copy(mode = UiMode.Error(error)) }
                }
            )
        }
    }

    fun runCatching(
        block: suspend () -> Unit,
        showLoading: Boolean
    ) {
        updateState(
            block = { block() },
            showLoading = showLoading
        )
    }

}