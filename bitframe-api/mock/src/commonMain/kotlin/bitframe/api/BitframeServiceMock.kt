package bitframe.api

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.client.spaces.SpacesServiceMock
import bitframe.authentication.client.users.UsersServiceMock
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService

interface BitframeServiceMock : BitframeService {
    companion object {
        operator fun invoke(
            config: BitframeServiceMockConfig
        ): BitframeServiceMock = object : BitframeServiceMock {
            override val config: BitframeServiceConfig = config
            override val spaces: SpacesService = SpacesServiceMock(config)
            override val users: UsersService = UsersServiceMock(config)
            override val signIn: SignInService = SignInServiceMock(config)
        }
    }
}