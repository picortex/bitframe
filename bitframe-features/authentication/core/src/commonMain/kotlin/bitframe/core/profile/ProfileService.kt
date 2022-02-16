@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core.profile

import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import bitframe.core.profile.params.ChangePasswordParams
import later.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
abstract class ProfileService(
    private val config: ServiceConfig
) {
    @JsName("_ignore_changePassword")
    abstract fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>): Later<ChangePasswordParams>
}