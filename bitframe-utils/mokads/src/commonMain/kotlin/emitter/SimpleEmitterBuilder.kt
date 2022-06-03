@file:Suppress("FunctionName")

package emitter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun <T> SimpleEmitterBuilder(
    on: Environment,
    scope: CoroutineScope,
    collector: (T) -> Unit
): EmitterBuilder<T> = object : EmitterBuilder<T> {
    override fun emit(value: T) {
        scope.launch(on.toDispatcher()) { collector(value) }
    }
}