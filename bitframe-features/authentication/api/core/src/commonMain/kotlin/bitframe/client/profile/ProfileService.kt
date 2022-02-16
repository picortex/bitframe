@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.profile

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.Session
import bitframe.core.profile.params.ChangePasswordParams
import bitframe.core.profile.params.RawChangePasswordParams
import bitframe.core.profile.params.toValidatedChangePasswordParams
import later.Later
import later.await
import later.later
import kotlin.js.JsExport
import bitframe.core.profile.ProfileService as CoreProfileService

@JsExport
class ProfileService(
    private val config: ServiceConfig
) : CoreProfileService(config) {
    private val scope get() = config.scope

    fun changePassword(params: RawChangePasswordParams): Later<ChangePasswordParams> = scope.later {
        val session = config.session.value as? Session.SignedIn ?: error("You need to be signed in first in order to change your password")
        val rb = RequestBody.Authorized(
            session = session,
            data = params.toValidatedChangePasswordParams()
        )
        changePassword(rb).await()
    }

    override fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>): Later<ChangePasswordParams> {
        TODO("Not yet implemented")
    }

    fun currentUserProfile(): Later<Any> {
        TODO()
    }
}