package bitframe.server.profile

import bitframe.core.profile.ProfileDaodService
import bitframe.server.ServiceConfig

class ProfileService(
    private val config: ServiceConfig
) : ProfileDaodService(config)