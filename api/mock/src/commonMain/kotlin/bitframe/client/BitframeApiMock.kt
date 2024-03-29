package bitframe.client

import bitframe.client.profile.ProfileService
import bitframe.client.profile.ProfileServiceMock
import bitframe.client.signin.SignInService
import bitframe.client.signin.SignInServiceMock
import bitframe.client.signout.SignOutService

interface BitframeApiMock : BitframeApi {
    companion object {
        operator fun invoke(
            config: BitframeApiConfigMock = BitframeApiConfigMock()
        ): BitframeApiMock = object : BitframeApiMock {
//            override val config: BitframeApiConfig by lazy { config }
//            override val spaces: SpacesService by lazy { SpacesServiceMock(config) }

            //            override val session: SessionAware get() = SessionAwareImpl(this)
            override val events: BitframeEvents by lazy { BitframeEvents(config.bus) }

            //            override val users: UsersService by lazy { UsersServiceMock(config) }
            override val signIn: SignInService by lazy { SignInServiceMock(config) }
            override val profile: ProfileService by lazy { ProfileServiceMock(config) }
            override val signOut: SignOutService by lazy { SignOutService(config) }
        }
    }
}