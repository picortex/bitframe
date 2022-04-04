package bitframe.client

class MicroScopeWithConstantsHookImpl<out I, out S, out C>(
    override val intents: I,
    override val state: S,
    override val constants: C
) : MicroScopeWithConstantsHook<I, S, C>