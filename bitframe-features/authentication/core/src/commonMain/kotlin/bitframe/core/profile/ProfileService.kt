@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core.profile

import bitframe.core.RequestBody
import bitframe.core.profile.params.ChangePasswordParams
import later.Later
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface ProfileService {
    @JsName("_ignore_changePassword")
    fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>): Later<ChangePasswordParams>
}