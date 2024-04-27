package multiplatform.ui.screens.debug.httpdetails

import multiplatform.ui.listeners.AppHttpCall

internal data class HttpDetailsState(
    val request: AppHttpCall = AppHttpCall(
        code = -1,
        body = "",
        headers = listOf(),
        timeStamp = "",
        method = "",
        url = ""
    )
)
