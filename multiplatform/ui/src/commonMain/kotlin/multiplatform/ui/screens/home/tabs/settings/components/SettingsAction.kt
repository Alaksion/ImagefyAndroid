package multiplatform.ui.screens.home.tabs.settings.components

sealed interface SettingsAction {
    data object Logout : SettingsAction
}