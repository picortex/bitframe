package bitframe.server

import bitframe.server.modules.Module
import bitframe.server.modules.authentication.modules.AuthenticationModule
import bitframe.server.modules.authentication.modules.AuthenticationModuleImpl

open class BitframeApplication<S : BitframeService>(
    open val config: BitframeApplicationConfig<S>
) {
    val authenticationModule: AuthenticationModule by lazy { AuthenticationModuleImpl(config.service) }
    open val modules: List<Module> get() = config.modules
}