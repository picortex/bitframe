@file:JsExport

package pimonitor.authentication.signup

import bitframe.authentication.signin.LoginConundrum
import later.Later
import pimonitor.Monitor
import kotlin.js.JsExport

abstract class SignUpService {
    abstract fun registerIndividuallyAs(person: IndividualRegistrationParams): Later<LoginConundrum>
    abstract fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum>
}