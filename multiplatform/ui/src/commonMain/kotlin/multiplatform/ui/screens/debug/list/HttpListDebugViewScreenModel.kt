package multiplatform.ui.screens.debug.list

import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.MutedLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.ui.listeners.AppHttpListener

internal class HttpListDebugViewScreenModel(
    dispatcher: CoroutineDispatcher,
    private val httpListener: AppHttpListener
) : StateScreenModel<DebugViewState>(
    dispatcher = dispatcher,
    logger = MutedLogger,
    initialState = DebugViewState()
) {

    fun initialize() {
        setState(
            block = {
                val list = httpListener.httpRequests.map {
                    it.toDebugViewRequestUiModel()
                }

                this.copy(
                    requests = list.toPersistentList()
                )
            }
        )
    }

}