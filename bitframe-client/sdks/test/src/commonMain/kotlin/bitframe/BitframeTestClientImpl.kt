package bitframe

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.*
import bitframe.authentication.users.*
import bitframe.events.EventBus

open class BitframeTestClientImpl(
    private val bus: EventBus,
    private val config: TestClientConfiguration,
    usersDao: UsersDao,
    spacesDao: SpacesDao
) : BitframeTestClient() {

    constructor(
        bus: EventBus,
        config: TestClientConfiguration,
        provider: AuthenticationDaoProvider = InMemoryAuthenticationDaoProvider()
    ) : this(bus, config, provider.users, provider.spaces)

    override val spaces: SpacesService = SpacesServiceImpl(config, spacesDao, usersDao)
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao, config)
    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, config, bus)
}