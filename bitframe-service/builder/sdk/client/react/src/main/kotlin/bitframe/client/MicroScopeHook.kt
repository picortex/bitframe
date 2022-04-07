@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

open class MicroScopeHook<out I, out S>(
    open val intents: I,
    open val state: S
)