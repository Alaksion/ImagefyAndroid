package multiplatform.ui.listeners

import kotlinx.coroutines.flow.SharedFlow

expect class LoginListener : Disposable {

    val loginEvent: SharedFlow<String>

}