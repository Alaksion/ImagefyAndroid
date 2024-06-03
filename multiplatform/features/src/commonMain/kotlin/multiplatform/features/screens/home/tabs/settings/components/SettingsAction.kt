package multiplatform.features.screens.home.tabs.settings.components

sealed interface SettingsAction {
    data object Logout : SettingsAction
    data object DebugView : SettingsAction
}