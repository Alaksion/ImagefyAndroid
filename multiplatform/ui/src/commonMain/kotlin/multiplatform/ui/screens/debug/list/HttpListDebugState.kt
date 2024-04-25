package multiplatform.ui.screens.debug.list

import io.github.alaksion.unsplashwrapper.platform.listeners.HttpResponse
import multiplatform.ui.utils.generateUUID

internal data class DebugViewState(
    val requests: List<DebugViewRequestUiModel> = listOf()
)

internal data class DebugViewRequestUiModel(
    val code: Int,
    val headers: List<Pair<String, String>>,
    val url: String,
    val timeStamp: String,
    val method: String,
) {
    val localId = generateUUID()
}

internal fun HttpResponse.toDebugViewRequestUiModel(): DebugViewRequestUiModel =
    DebugViewRequestUiModel(
        code = this.code,
        headers = this.headers.map { Pair(it.name, it.value) },
        url = this.url,
        timeStamp = this.timeStamp,
        method = this.method
    )
