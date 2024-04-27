package multiplatform.ui.screens.debug.httplist

import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.MutedLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.ui.listeners.AppHttpListener

internal class HttpListScreenModel(
    dispatcher: CoroutineDispatcher,
    private val httpListener: AppHttpListener
) : StateScreenModel<HtppListState>(
    dispatcher = dispatcher,
    logger = MutedLogger,
    initialState = HtppListState()
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