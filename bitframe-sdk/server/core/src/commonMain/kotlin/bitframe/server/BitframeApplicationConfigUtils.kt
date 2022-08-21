package bitframe.server

import bitframe.Module

fun <A : BitframeApplicationConfig<*>> A.with(vararg mods: Module): A {
    modules.addAll(mods)
    return this
}