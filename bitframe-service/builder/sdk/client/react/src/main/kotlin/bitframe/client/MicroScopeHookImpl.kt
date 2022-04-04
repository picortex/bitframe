package bitframe.client

class MicroScopeHookImpl<out I, out S>(
    override val intents: I,
    override val state: S
) : MicroScopeHook<I, S>