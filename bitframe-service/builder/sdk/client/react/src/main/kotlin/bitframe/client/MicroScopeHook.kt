@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

open class MicroScopeHook<out IW, out S>(
    open val intents: IW,
    open val state: S
)