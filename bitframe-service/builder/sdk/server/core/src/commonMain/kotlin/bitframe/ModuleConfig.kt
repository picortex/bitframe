package bitframe

import bitframe.internal.ModuleConfigImpl

interface ModuleConfig<out C, out E> {
    val controller: C
    val endpoint: E

    companion object {
        operator fun <C, E> invoke(controller: C, endpoint: E): ModuleConfig<C, E> = ModuleConfigImpl(controller, endpoint)
    }
}