@file:JsExport

package pimonitor

import bitframe.authentication.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import pimonitor.monitors.MonitorsService
import pimonitor.server.authentication.signup.SignUpService
import pimonitor.server.businesses.BusinessesService
import kotlin.js.JsExport

abstract class PiMonitorService(
    val spaces: SpacesService,
    val users: UsersService,
    val signIn: SignInService,
    val signUp: SignUpService,
    val monitors: MonitorsService,
    val businesses: BusinessesService,
//    override val cache: Cache,
    val bus: EventBus = InMemoryEventBus(),
) //: BitframeService()