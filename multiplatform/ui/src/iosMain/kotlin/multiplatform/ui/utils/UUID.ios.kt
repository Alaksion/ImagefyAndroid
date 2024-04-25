package multiplatform.ui.utils

import platform.Foundation.NSUUID

actual fun generateUUID(): String {
    return NSUUID.UUID().UUIDString
}