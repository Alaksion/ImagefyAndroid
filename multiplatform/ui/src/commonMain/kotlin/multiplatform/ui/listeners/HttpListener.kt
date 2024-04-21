package multiplatform.ui.listeners

import io.github.alaksion.unsplashwrapper.platform.listeners.HttpListener
import io.github.alaksion.unsplashwrapper.platform.listeners.HttpResponse
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

internal class AppHttpListener : HttpListener {

    private var _httpRequests = persistentListOf<HttpResponse>()

    val httpRequests
        get() = _httpRequests

    override fun onReceive(httpResponse: HttpResponse) {
        _httpRequests = (_httpRequests + httpResponse).toPersistentList()
    }
}