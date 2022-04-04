@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

@JsExport
interface MicroScopeHook<out I, out S> {
    val intents: I
    val state: S
}