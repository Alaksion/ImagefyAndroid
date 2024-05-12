package multiplatform.uiEvent

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import multiplatform.foundation.utils.uuid.generateUUID

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
        if (_events.value.isNotEmpty()) return
        _events.update { it + UiEvent(data = event, id = generateUUID()) }
    }

    override fun consumeEvent(consumedEvent: UiEvent<T>) {
        _events.update { eventQueue ->
            eventQueue.filter { it.id != consumedEvent.id }
        }
    }

}