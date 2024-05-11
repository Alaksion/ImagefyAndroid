package multiplatform.foundation.utils.uuid

import java.util.UUID

actual fun generateUUID(): String {
    return UUID.randomUUID().toString()
}