package multiplatform.ui.screens.debug.httpdetails

import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.MutedLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.ui.listeners.AppHttpListener

internal class HttpDetailsScreenModel(
    dispatcher: CoroutineDispatcher,
    private val httpListener: AppHttpListener
) : StateScreenModel<HttpDetailsState>(
    dispatcher = dispatcher,
    logger = MutedLogger,
    initialState = HttpDetailsState()
) {
}