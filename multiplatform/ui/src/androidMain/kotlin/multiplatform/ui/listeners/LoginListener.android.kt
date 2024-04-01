package multiplatform.ui.listeners

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.core.util.Consumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

actual class LoginListener(
    private val activity: ComponentActivity,
    private val scope: CoroutineScope
) : Disposable {

    private val consumer = Consumer<Intent> { intent ->
        if (intent.action == Intent.ACTION_VIEW) {
            val authCode = intent.data?.getQueryParameter("code")
            authCode?.let {
                scope.launch {
                    _loginEvent.emit(it)
                }
            }
        }
    }

    init {
        activity.addOnNewIntentListener(consumer)
    }

    private val _loginEvent = MutableSharedFlow<String>()
    actual val loginEvent: SharedFlow<String> = _loginEvent

    override fun onDispose() {
        activity.removeOnNewIntentListener(consumer)
    }

}