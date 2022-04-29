@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package emitter

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Emitter<T> {

    companion object {
        internal val DEFAULT_ENVIRONMENT by lazy { Environment.Background }
    }

    fun <R> map(on: Environment = DEFAULT_ENVIRONMENT, transformer: (T) -> R): Emitter<R>

    fun onError(recoverer: ErrorRecoverer<T>): Emitter<T>

    @JsName("_ignore_collect")
    fun collect(on: Environment = DEFAULT_ENVIRONMENT, collector: (T) -> Unit)

    fun collect(collector: (T) -> Unit)

    fun ceaseAndCollect(collector: (T) -> Unit)
}