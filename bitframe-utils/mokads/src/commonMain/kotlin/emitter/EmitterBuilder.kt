@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package emitter

import kotlin.js.JsExport

interface EmitterBuilder<in T> {
    fun emit(value: T)
}