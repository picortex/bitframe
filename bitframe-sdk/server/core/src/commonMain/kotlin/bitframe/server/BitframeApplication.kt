package bitframe.server

open class BitframeApplication<S : BitframeService>(
    open val config: BitframeApplicationConfig<S>
) {
    val authenticationModule: AuthenticationModule by lazy { AuthenticationModule(config.service.signIn) }
    open val modules: List<Module> get() = config.modules

    open suspend fun onStart(service: S) {}
}