package bitframe.server

internal class BitframeApplicationConfigImpl<out S>(
    override val service: S,
    override val modules: MutableList<Module>
) : BitframeApplicationConfig<S>