package bitframe.client.profile

import bitframe.client.MockServiceConfig
import bitframe.core.profile.ProfileDaodService
import bitframe.core.profile.ProfileService as CoreProfileService

class ProfileServiceMock(
    private val config: MockServiceConfig = MockServiceConfig()
) : ProfileService(config), CoreProfileService by ProfileDaodService(config)