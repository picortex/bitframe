package bitframe.server

import bitframe.server.data.DAOProvider
import bitframe.server.modules.Module

fun <T : DAOProvider, A : BitframeApplicationConfig<T>> A.with(vararg mods: Module): A {
    modules.addAll(mods)
    return this
}