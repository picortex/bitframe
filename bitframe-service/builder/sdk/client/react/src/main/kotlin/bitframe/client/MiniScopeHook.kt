@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

class MiniScopeHook<out I, out S, out C>(
    override val intents: I,
    override val state: S,
    val constants: C
) : MicroScopeHook<I, S>(intents, state)