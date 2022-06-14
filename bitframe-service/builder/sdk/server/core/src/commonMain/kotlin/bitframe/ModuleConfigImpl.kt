package bitframe

class ModuleConfigImpl<out C, out E>(
    override val controller: C,
    override val endpoint: E
) : ModuleConfig<C, E>