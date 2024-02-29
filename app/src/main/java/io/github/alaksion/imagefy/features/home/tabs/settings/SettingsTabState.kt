package io.github.alaksion.imagefy.features.home.tabs.settings

internal data class SettingsTabState(
    val showLogoutButton: Boolean = false
)

internal sealed interface SettingsTabEvents {
    data object Logout : SettingsTabEvents
}