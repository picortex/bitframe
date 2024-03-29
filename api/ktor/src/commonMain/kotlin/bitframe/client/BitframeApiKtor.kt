package bitframe.client

import bitframe.client.profile.ProfileService
import bitframe.client.profile.ProfileServiceKtor
import bitframe.client.signin.SignInService
import bitframe.client.signin.SignInServiceKtor
import bitframe.client.signout.SignOutService
import bitframe.core.rest.AuthEndpoint

interface BitframeApiKtor : BitframeApi {
    companion object {
        operator fun invoke(
            config: BitframeApiKtorConfig
        ): BitframeApiKtor = object : BitframeApiKtor {
//            override val config: BitframeApiConfig by lazy { config }

            //            override val spaces: SpacesService by lazy { SpacesServiceKtor(config) }
//            override val session: SessionAware get() = SessionAwareImpl(this)
            override val events: BitframeEvents by lazy { BitframeEvents(config.bus) }

            //            override val users: UsersService by lazy { UsersServiceKtor(config) }
            override val signIn: SignInService by lazy { SignInServiceKtor(config.map { AuthEndpoint.Client(config.url, "v1") }) }
            override val profile: ProfileService by lazy { ProfileServiceKtor(config.map { AuthEndpoint.Client(config.url, "v1") }) }
            override val signOut: SignOutService by lazy { SignOutService(config) }
        }
    }
}