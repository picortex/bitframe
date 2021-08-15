package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginService

interface BitframeService {
    val config: ClientConfiguration
    val authentication: LoginService
}