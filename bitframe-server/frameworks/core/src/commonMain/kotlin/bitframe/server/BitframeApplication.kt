package bitframe.server

import bitframe.server.data.DAOProvider
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.modules.AuthenticationModule
import bitframe.server.modules.authentication.modules.AuthenticationModuleConfig
import bitframe.server.modules.authentication.modules.AuthenticationModuleImpl

open class BitframeApplication<T : DAOProvider>(
    config: BitframeApplicationConfig<T>
) {
    private val authModuleConfig = AuthenticationModuleConfig(config)
    val authenticationModule: AuthenticationModule = AuthenticationModuleImpl(config)
    open val modules: List<Module> = config.modules
}