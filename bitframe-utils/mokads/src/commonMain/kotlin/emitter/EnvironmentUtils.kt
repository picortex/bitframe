package emitter

import kotlinx.coroutines.Dispatchers

internal fun Environment.toDispatcher() = when (this) {
    Environment.Background -> Dispatchers.Default
    Environment.Main -> Dispatchers.Main
}