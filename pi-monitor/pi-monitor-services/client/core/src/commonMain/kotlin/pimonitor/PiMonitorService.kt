@file:JsExport

package pimonitor

import bitframe.BitframeService
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import cache.Cache
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitors.MonitorsService
import kotlin.js.JsExport

abstract class PiMonitorService(
    override val spaces: SpacesService,
    override val users: UsersService,
    override val signIn: SignInService,
    val signUp: SignUpService,
    val monitors: MonitorsService,
    val businesses: BusinessesService,
    override val cache: Cache,
    val bus: EventBus = InMemoryEventBus(),
) : BitframeService()