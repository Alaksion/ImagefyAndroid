package multiplatform.stateScreenmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StateScreenModel<T>(
    private val dispatcher: CoroutineDispatcher,
    private val logger: StateLogger = MutedLogger,
    initialState: T,
    initialMode: UiMode = UiMode.Content
) : ScreenModel {

    private val _state = MutableStateFlow<UiState<T>>(
        UiState(
            data = initialState,
            mode = initialMode
        )
    )

    val state: StateFlow<UiState<T>> = _state

    protected val currentData: T
        get() {
            return state.value.data
        }

    private val stateUpdater = StateUpdater(_state)

    fun setState(
        block: suspend T.() -> T,
        showLoading: Boolean = true,
        updateModeSuccess: Boolean = true,
    ): Job = updateState(
        block = {
            val result = block(_state.value.data)
            update { result }
        },
        showLoading = showLoading,
        updateModeSuccess = updateModeSuccess
    )

    fun updateState(
        block: suspend StateUpdater<T>.() -> Unit,
        showLoading: Boolean = true,
        updateModeSuccess: Boolean = true,
    ): Job = screenModelScope.launch(dispatcher) {
        if (showLoading)
            _state.update { old -> old.copy(mode = UiMode.Loading) }
        runCatching {
            block(stateUpdater)
        }.fold(
            onSuccess = {
                if (updateModeSuccess) {
                    _state.update { old -> old.copy(mode = UiMode.Content) }
                }
            },
            onFailure = { error ->
                logger.log(error.message.orEmpty(), type = LogType.Error)
                _state.update { old -> old.copy(mode = UiMode.Error(error)) }
            }
        )
    }

    fun runCatching(
        block: suspend () -> Unit,
        showLoading: Boolean = true,
        updateModeSuccess: Boolean = true,
    ): Job = updateState(
        block = {
            block()
        },
        showLoading = showLoading,
        updateModeSuccess = updateModeSuccess
    )

}