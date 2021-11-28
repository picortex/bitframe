package bitframe.monitors

import bitframe.authentication.users.UserRef
import bitframe.daos.conditions.Condition
import later.Later
import bitframe.authentication.signup.SignUpParams

interface MonitorDao {
    fun create(params: SignUpParams, ref: UserRef): Later<Monitor>
    fun all(where: Condition<String, Any>? = null): Later<List<Monitor>>
}