@file:JsExport

package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import live.Live
import kotlin.js.JsExport
import bitframe.authentication.signin.Session as SignInSession

abstract class MonitorsService(
    open val config: ServiceConfig
) {
    abstract fun load(uid: String): Later<Monitor?>
    abstract fun monitor(with: UserRef): Later<Monitor>
}