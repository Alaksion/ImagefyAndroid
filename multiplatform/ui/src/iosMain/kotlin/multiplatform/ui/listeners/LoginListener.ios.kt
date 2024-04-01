package multiplatform.ui.listeners

import kotlinx.coroutines.flow.SharedFlow

actual class LoginListener : Disposable {

    actual val loginEvent: SharedFlow<String>
        get() = TODO("Not yet implemented")

    override fun onDispose() {
        TODO("Not yet implemented")
    }

}