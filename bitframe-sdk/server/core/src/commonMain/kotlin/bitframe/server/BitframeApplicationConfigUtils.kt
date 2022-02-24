package bitframe.server

fun <A : BitframeApplicationConfig<*>> A.with(vararg mods: Module): A {
    modules.addAll(mods)
    return this
}