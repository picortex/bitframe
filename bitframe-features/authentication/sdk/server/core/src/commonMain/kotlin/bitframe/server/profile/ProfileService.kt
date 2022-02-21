package bitframe.server.profile

import bitframe.core.profile.ProfileDaodService
import bitframe.server.ServiceConfig

class ProfileService(
    override val config: ServiceConfig
) : ProfileDaodService(config)