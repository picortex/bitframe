package pimonitor.authentication

import bitframe.server.modules.authentication.services.AuthenticationService
import pimonitor.server.authentication.signup.SignUpService

interface PiMonitorAuthenticationService : AuthenticationService {
    val signUp: SignUpService
}