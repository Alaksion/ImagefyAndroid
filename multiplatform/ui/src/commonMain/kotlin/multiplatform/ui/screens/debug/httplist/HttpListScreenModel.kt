package multiplatform.ui.screens.debug.httplist

import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.ui.listeners.AppHttpListener

internal class HttpListScreenModel(
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
    private val httpListener: AppHttpListener
) : StateScreenModel<HtppListState>(
    dispatcher = dispatcher,
    logger = logger,
    initialState = HtppListState()
) {
    var isInitialized = false

    fun initialize() {
        if (isInitialized.not()) {
            setState(
                block = {
                    val list = httpListener.httpRequests

                    this.copy(
                        requests = list.toPersistentList()
                    )
                }
            ).invokeOnCompletion {
                if (it == null) isInitialized = true
            }
        }
    }

}