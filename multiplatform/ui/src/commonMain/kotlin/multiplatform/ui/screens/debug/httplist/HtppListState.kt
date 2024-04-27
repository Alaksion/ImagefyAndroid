package multiplatform.ui.screens.debug.httplist

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import multiplatform.ui.listeners.AppHttpCall

internal data class HtppListState(
    val requests: ImmutableList<AppHttpCall> = persistentListOf()
)