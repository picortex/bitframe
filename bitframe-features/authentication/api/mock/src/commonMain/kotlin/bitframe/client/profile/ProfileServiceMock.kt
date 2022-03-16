package bitframe.client.profile

import bitframe.client.ServiceConfigMock
import bitframe.core.profile.ProfileDaodService
import bitframe.core.profile.ProfileService as CoreProfileService

class ProfileServiceMock(
    private val config: ServiceConfigMock = ServiceConfigMock()
) : ProfileService(config), CoreProfileService by ProfileDaodService(config)