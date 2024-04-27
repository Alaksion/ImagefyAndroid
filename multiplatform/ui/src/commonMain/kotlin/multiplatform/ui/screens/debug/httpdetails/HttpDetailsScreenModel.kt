package multiplatform.ui.screens.debug.httpdetails

import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.ui.listeners.AppHttpListener

internal class HttpDetailsScreenModel(
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
    private val httpListener: AppHttpListener
) : StateScreenModel<HttpDetailsState>(
    dispatcher = dispatcher,
    logger = logger,
    initialState = HttpDetailsState()
) {
}