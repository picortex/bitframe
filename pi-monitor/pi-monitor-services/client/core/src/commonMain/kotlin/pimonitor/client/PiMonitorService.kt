@file:JsExport

package pimonitor.client

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.client.BitframeService
import pimonitor.authentication.signup.SignUpService
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.client.monitors.MonitorsService
import kotlin.js.JsExport

abstract class PiMonitorService(
    override val config: PiMonitorServiceConfig
) : BitframeService(config) {
    abstract override val spaces: SpacesService
    abstract override val users: UsersService
    abstract override val signIn: SignInService
    abstract val signUp: SignUpService
    abstract val monitors: MonitorsService
    abstract val businesses: BusinessesService
}