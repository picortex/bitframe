@file:JsExport

package bitframe.server

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.spaces.SpacesService
import bitframe.authentication.server.users.UsersService
import bitframe.server.authentication.signup.SignUpService
import bitframe.server.businesses.BusinessesService
import bitframe.server.monitors.MonitorsService
import kotlin.js.JsExport

class PiMonitorService(override val config: PiMonitorServiceConfig) : BitframeService(config) {
    override val spaces: SpacesService = SpacesService(config)
    override val users: UsersService = UsersService(config)
    override val signIn: SignInService = SignInService(config)
    val signUp: SignUpService = SignUpService(config.with(users))
    val monitors: MonitorsService = MonitorsService(config)
    val businesses: BusinessesService = BusinessesService(config)
}