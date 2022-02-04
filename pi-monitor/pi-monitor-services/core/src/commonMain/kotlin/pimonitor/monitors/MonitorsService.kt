@file:JsExport

package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.service.config.ServiceConfig
import later.Later
import kotlin.js.JsExport

abstract class MonitorsService(
    open val config: ServiceConfig
) {
    abstract fun load(uid: String): Later<Monitor?>
    abstract fun monitor(with: UserRef): Later<Monitor>
}