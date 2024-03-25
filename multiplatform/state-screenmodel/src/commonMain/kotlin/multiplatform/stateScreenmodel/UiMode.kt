package multiplatform.stateScreenmodel

sealed class UiMode {
    data object Loading : UiMode()
    data object Content : UiMode()
    data class Error(val error: Throwable) : UiMode()
}