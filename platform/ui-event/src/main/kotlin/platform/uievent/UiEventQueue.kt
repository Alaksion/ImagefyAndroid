package platform.uievent

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class UiEvent<T>(
    val id: String,
    val data: T,
)

interface UiEventQueue<T> {
    val events: StateFlow<List<UiEvent<T>>>
    fun emitEvent(event: T)
    fun consumeEvent(consumedEvent: UiEvent<T>)
}

class UiEventQueueHandler<T> : UiEventQueue<T> {

    private val _events = MutableStateFlow(listOf<UiEvent<T>>())

    override val events: StateFlow<List<UiEvent<T>>> = _events

    override fun emitEvent(event: T) {
        if (_events.value.size > 0) return
        _events.update { it + UiEvent(data = event, id = UUID.randomUUID().toString()) }
    }

    override fun consumeEvent(consumedEvent: UiEvent<T>) {
        _events.update { eventQueue ->
            eventQueue.filter { it.id != consumedEvent.id }
        }
    }

}