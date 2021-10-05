package bitframe.authentication.signin

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.daos.conditions.contains
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later

class SignInServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    private val config: ServiceConfig
) : SignInService() {
    constructor(provider: AuthenticationDaoProvider, config: ServiceConfig) : this(provider.users, provider.spaces, config)

    private val scope = config.scope
    override fun signIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        validate(credentials)
        val matches = usersDao.all(where = "contacts" contains credentials.alias).await()
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(
            user = match,
            spaces = match.spaces
        ).also {
            session.value = if (it.spaces.size > 1) {
                Session.Conundrum(App(config.appId), it.spaces, it.user)
            } else {
                Session.SignedIn(App(config.appId), it.spaces.first(), it.user)
            }
        }
    }

    override fun resolve(space: Space): Later<Session.SignedIn> = scope.later {
        val error = IllegalStateException(
            """
                You are attempting to resolve a non exiting conundrum,
                
                Make sure you have tried to signIn and the result obtained was a LoginConundrum with more that one space
                """.trimIndent()
        )
        when (val s = session.value) {
            Session.Unknown -> throw error
            is Session.SignedIn -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.Conundrum -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.SignedOut -> throw error
        }.also { session.value = it }
    }
}