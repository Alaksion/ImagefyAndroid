package multiplatform.ui.listeners

import io.github.alaksion.unsplashwrapper.platform.listeners.HttpListener
import io.github.alaksion.unsplashwrapper.platform.listeners.HttpResponse

internal class AppHttpListener : HttpListener {

    private var _httpRequests = mutableListOf<HttpResponse>()

    val httpRequests: List<HttpResponse>
        get() = _httpRequests

    override fun onReceive(httpResponse: HttpResponse) {
        _httpRequests.add(httpResponse)
    }
}