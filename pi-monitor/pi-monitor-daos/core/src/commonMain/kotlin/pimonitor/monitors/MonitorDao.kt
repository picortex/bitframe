package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.daos.conditions.Condition
import later.Later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.monitors.Monitor

interface MonitorDao {
    fun create(params: SignUpParams, ref: UserRef): Later<Monitor>
    fun all(where: Condition<String, Any>? = null): Later<List<Monitor>>
}