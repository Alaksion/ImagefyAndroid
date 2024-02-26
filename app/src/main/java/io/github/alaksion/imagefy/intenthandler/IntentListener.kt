package io.github.alaksion.imagefy.intenthandler

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.util.Consumer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ComponentActivity.IntentListener(
    handle: (Intent) -> Unit
) {
    LaunchedEffect(Unit) {
        val flow = callbackFlow<Intent> {
            val consumer = Consumer<Intent> { trySend(it) }

            this@IntentListener.addOnNewIntentListener(consumer)
            awaitClose { this@IntentListener.removeOnNewIntentListener(consumer) }

        }
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            flow.collectLatest { intent -> handle(intent) }
        }
    }
}