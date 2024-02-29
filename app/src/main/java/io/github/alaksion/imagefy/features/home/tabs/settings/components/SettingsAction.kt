package io.github.alaksion.imagefy.features.home.tabs.settings.components

sealed interface SettingsAction {
    data object Logout : SettingsAction
}