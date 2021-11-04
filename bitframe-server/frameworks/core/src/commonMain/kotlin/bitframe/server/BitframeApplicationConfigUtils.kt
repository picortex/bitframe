package bitframe.server

import bitframe.server.modules.Module

fun <A : BitframeApplicationConfig<*>> A.with(vararg mods: Module): A {
    modules.addAll(mods)
    return this
}