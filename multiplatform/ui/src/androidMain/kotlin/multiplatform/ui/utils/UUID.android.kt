package multiplatform.ui.utils

import java.util.UUID

actual fun generateUUID(): String {
    return UUID.randomUUID().toString()
}