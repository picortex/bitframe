package bitframe.client.profile

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.profile.params.ChangePasswordParams
import later.Later

class ProfileServiceKtor(
    private val config: ServiceConfig
) : ProfileService(config) {
    override fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>): Later<ChangePasswordParams> {
        TODO("Not yet implemented")
    }
}