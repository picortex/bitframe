package bitframe

import koncurrent.Executor
import kotlinx.serialization.StringFormat

interface ServerConfig<out S, out E> {
    val endpoint: E
    val service: S
    val executor: Executor
    val codec: StringFormat
}