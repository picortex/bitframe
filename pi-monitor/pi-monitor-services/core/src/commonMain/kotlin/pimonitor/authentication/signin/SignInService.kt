package pimonitor.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.daos.conditions.isEqualTo
import bitframe.service.config.ServiceConfig
import later.await
import later.later
import pimonitor.monitors.Monitor
import pimonitor.monitors.MonitorDao
import bitframe.authentication.signin.SignInServiceImpl as BitframeSignInService

class SignInServiceImpl(
    private val monitors: MonitorDao,
    private val users: UsersDao,
    private val spacesDao: SpacesDao,
    override val config: ServiceConfig
) : SignInService<Monitor>() {
    private val signInService = BitframeSignInService(users, spacesDao, config)
    override fun executeSignIn(credentials: SignInCredentials) = signInService.executeSignIn(credentials)

    override fun makeSession(app: App, space: Space, user: User) = scope.later {
        val monitor: Monitor = monitors.all(where = "uid" isEqualTo user.uid).await().first()
        Session.SignedIn(app, space, user, monitor)
    }
}