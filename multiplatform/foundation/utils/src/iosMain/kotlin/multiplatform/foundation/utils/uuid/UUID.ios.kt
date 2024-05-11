package multiplatform.foundation.utils.uuid

import platform.Foundation.NSUUID

actual fun generateUUID(): String {
    return NSUUID.UUID().UUIDString
}