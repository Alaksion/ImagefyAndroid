package platform.uievent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T> UiEventEffect(
    eventSource: UiEventQueue<T>,
    onEvent: (T) -> Unit
) {
    LaunchedEffect(eventSource) {
        eventSource.events.collectLatest { eventQueue ->
            eventQueue.firstOrNull()?.let { event ->
                onEvent(event.data)
                eventSource.consumeEvent(event)
            }
        }
    }
}