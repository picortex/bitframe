package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginService
import platform.Platform

interface BitframeService {
    val platform: Platform get() = Platform
    val config: ClientConfiguration
    val authentication: LoginService
}