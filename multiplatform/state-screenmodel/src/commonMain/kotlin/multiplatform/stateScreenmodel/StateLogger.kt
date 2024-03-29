package multiplatform.stateScreenmodel

interface StateLogger {
    fun log(message: String, type: LogType)
}

object MutedLogger : StateLogger {
    override fun log(message: String, type: LogType) = Unit
}

enum class LogType {
    Debug,
    Error,
}