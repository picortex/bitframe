@file:JsExport

package pimonitor.server

import bitframe.authentication.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import pimonitor.server.authentication.signup.SignUpService
import pimonitor.server.businesses.BusinessesService
import pimonitor.server.monitors.MonitorsService
import kotlin.js.JsExport

abstract class PiMonitorService(open val config: PiMonitorServiceConfig) {
    abstract val spaces: SpacesService
    abstract val users: UsersService
    abstract val signIn: SignInService
    abstract val signUp: SignUpService
    abstract val monitors: MonitorsService
    abstract val businesses: BusinessesService
}