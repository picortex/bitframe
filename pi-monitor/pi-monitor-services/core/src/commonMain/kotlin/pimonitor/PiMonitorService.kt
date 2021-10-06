@file:JsExport

package pimonitor

import bitframe.BitframeService
import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService
import pimonitor.authentication.signup.SignUpService
import pimonitor.evaluation.businesses.BusinessService
import kotlin.js.JsExport

abstract class PiMonitorService(
    override val users: UsersService,
    override val signIn: SignInService,
    val signUp: SignUpService,
    val businesses: BusinessService,
) : BitframeService