package multiplatform.ui.listeners

import kotlinx.coroutines.flow.SharedFlow

actual class LoginListener : Disposable {

    actual val loginEvent: SharedFlow<String>
        get() = TODO("LoginListener is not defined to IOS target. Please provide an implementation")

    override fun onDispose() {
        TODO("LoginListener is not defined to IOS target. Please provide an implementation")
    }

}