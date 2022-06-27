package bitframe.internal

import bitframe.ModuleConfig

class ModuleConfigImpl<out C, out E>(
    override val controller: C,
    override val endpoint: E
) : ModuleConfig<C, E>