package multiplatform.ui.app

import multiplatform.ui.listeners.LoginListener

data class ImagefyAppPlugin(
    val keys: ImagefyAppKeys,
    val loginListener: LoginListener,
)

data class ImagefyAppKeys(
    val private: String,
    val apiKey: String,
)
