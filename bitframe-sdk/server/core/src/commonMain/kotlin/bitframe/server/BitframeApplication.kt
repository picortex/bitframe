package bitframe.server

import bitframe.Module

open class BitframeApplication<S>(
    open val config: BitframeApplicationConfig<S>
) {
    //    val authenticationModule: AuthenticationModule by lazy {
//        val controller = AuthenticationController(
//            signInService = config.service.signin,
//            profileService = config.service.profile
//        )
//        AuthenticationModule(controller)
//    }
    open val modules: List<Module> get() = config.modules

    open suspend fun onStart(service: S) {}

    open suspend fun onExit() {}
}