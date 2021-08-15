package bitframe.server

import bitframe.server.modules.Module
import bitframe.server.modules.authentication.AuthenticationModule

open class BitframeApplication(
    val authenticationModule: AuthenticationModule,
    open val modules: List<Module>
)