package bitframe.client.profile

import bitframe.client.ServiceConfig
import later.Later
import bitframe.core.profile.ProfileService as CoreProfileService

class ProfileService(
    private val config: ServiceConfig
) : CoreProfileService(config) {
    fun currentUserProfile(): Later<Any> {
        TODO()
    }
}