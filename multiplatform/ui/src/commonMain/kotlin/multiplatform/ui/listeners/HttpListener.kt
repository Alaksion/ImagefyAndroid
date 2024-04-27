package multiplatform.ui.listeners

import io.github.alaksion.unsplashwrapper.platform.listeners.HttpHeader
import io.github.alaksion.unsplashwrapper.platform.listeners.HttpListener
import io.github.alaksion.unsplashwrapper.platform.listeners.HttpResponse
import multiplatform.ui.utils.generateUUID

internal class AppHttpListener : HttpListener {

    private var _httpRequests = mutableListOf<AppHttpCall>()

    val httpRequests: List<AppHttpCall>
        get() = _httpRequests

    override fun onReceive(httpResponse: HttpResponse) {
        val mapped = AppHttpCall(
            code = httpResponse.code,
            body = httpResponse.body,
            headers = httpResponse.headers,
            timeStamp = httpResponse.timeStamp,
            url = httpResponse.url,
            method = httpResponse.method,
        )
        _httpRequests.add(mapped)
    }
}

internal data class AppHttpCall(
    val code: Int,
    val body: String,
    val headers: List<HttpHeader>,
    val timeStamp: String,
    val url: String,
    val method: String,
) {
    val localId: String = generateUUID()
}