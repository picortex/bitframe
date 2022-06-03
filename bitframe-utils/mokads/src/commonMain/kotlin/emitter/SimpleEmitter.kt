package emitter

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.jvm.JvmOverloads

class SimpleEmitter<T> @JvmOverloads constructor(
    private val on: Environment = Emitter.DEFAULT_ENVIRONMENT,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + CoroutineName("EmitterScope")),
    private val emitter: (EmitterBuilder<T>) -> Unit
) : Emitter<T> {

    private var recoverer: ErrorRecoverer<T>? = null

    override fun collect(on: Environment, collector: (T) -> Unit) {
        val builder = SimpleEmitterBuilder(on, scope, collector)
        val emittingDispatcher = this.on.toDispatcher()
        scope.launch(emittingDispatcher) {
            try {
                emitter(builder)
            } catch (err: Throwable) {
                val r = recoverer
                if (r != null) r(builder, err) else throw err
            }
        }
    }

    override fun onError(recoverer: ErrorRecoverer<T>): Emitter<T> {
        this.recoverer = recoverer
        return this
    }

    override fun <R> map(on: Environment, transformer: (T) -> R): Emitter<R> = SimpleEmitter(on, scope) { emitter ->
        collect(on) { emitter.emit(transformer(it)) }
    }

    override fun collect(collector: (T) -> Unit) = collect(on = Emitter.DEFAULT_ENVIRONMENT, collector)

    override fun ceaseAndCollect(collector: (T) -> Unit) = collect(collector)
}