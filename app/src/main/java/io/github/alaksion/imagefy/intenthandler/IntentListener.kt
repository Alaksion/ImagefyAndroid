package io.github.alaksion.imagefy.intenthandler

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.core.util.Consumer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun IntentEffect(
    activity: ComponentActivity,
    onIntent: (Intent) -> Unit
) {
    DisposableEffect(key1 = Unit) {
        val consumer = Consumer<Intent> { intent ->
            if (intent.action == Intent.ACTION_VIEW) {
                onIntent(intent)
            }
        }
        activity.addOnNewIntentListener(consumer)
        onDispose { activity.removeOnNewIntentListener(consumer) }
    }
}