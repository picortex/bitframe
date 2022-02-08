package bitframe.api

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.client.signout.SignOutService
import bitframe.authentication.client.spaces.SpacesServiceMock
import bitframe.authentication.client.users.UsersServiceMock
import bitframe.actors.spaces.SpacesService
import bitframe.actors.users.UsersService

interface BitframeServiceMock : BitframeService {
    companion object {
        operator fun invoke(
            config: BitframeServiceMockConfig = BitframeServiceMockConfig()
        ): BitframeServiceMock = object : BitframeServiceMock {
            override val config: BitframeServiceConfig by lazy { config }
            override val spaces: SpacesService by lazy { SpacesServiceMock(config) }
            override val users: UsersService by lazy { UsersServiceMock(config) }
            override val signIn: SignInService by lazy { SignInServiceMock(config) }
            override val signOut: SignOutService by lazy { SignOutService(config) }
        }
    }
}