@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

@JsExport
interface MicroScopeWithConstantsHook<out I, out S, out C> : MicroScopeHook<I, S> {
    val constants: C
}