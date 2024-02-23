package platform.uievent

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class UiEvent<T>(
    val id: Int,
    val data: T,
)

interface UiEventHolder<T> {
    fun emitEvent(event: T)
    fun consumeEvent()
}

class UiEventImpl<T> : UiEventHolder<T> {

    private val events = MutableStateFlow(listOf<UiEvent<T>>())

    override fun emitEvent(event: T) {
        events.update { it + UiEvent(data = event, id = 1) }
    }

    override fun consumeEvent() {
        TODO("Not yet implemented")
    }

}