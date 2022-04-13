@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

class MiniScopeHook<out IW, out S, out C>(
    override val intents: IW,
    override val state: S,
    val constants: C
) : MicroScopeHook<IW, S>(intents, state)