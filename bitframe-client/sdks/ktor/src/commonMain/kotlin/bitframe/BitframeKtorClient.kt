package bitframe

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.signin.SignInServiceKtorConfig
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import bitframe.service.client.config.KtorClientConfiguration
import cache.Cache

open class BitframeKtorClient(
    val bus: EventBus,
    val config: KtorClientConfiguration
) : BitframeService() {
    override val cache: Cache get() = TODO("Not yet implemented")
    override val spaces: SpacesService get() = TODO("Not yet implemented")
    override val users: UsersService get() = TODO("Not yet implemented")
    override val signIn: SignInService = SignInServiceKtor(
        config = SignInServiceKtorConfig(
            appId = config.appId, scope = config.scope, cache = cache, bus = bus, http = config.http, url = config.url
        )
    )
}