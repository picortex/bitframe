package bitframe.authentication.service.daod.usecase

import bitframe.actors.users.User
import bitframe.actors.users.UserEmail
import bitframe.actors.users.UserPhone
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.signin.SignInUseCase
import bitframe.authentication.users.UserCredentials
import bitframe.daos.CompoundDao
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.exceptions.EntityNotFoundException
import bitframe.daos.get
import bitframe.service.daod.config.DaodServiceConfig
import bitframe.service.requests.RequestBody
import later.Later
import later.await
import later.later

class SignInDaodUseCase(val config: DaodServiceConfig) : SignInUseCase {
    private val scope get() = config.scope
    private val usersDao by lazy { config.daoFactory.get<User>() }
    private val credentialsDao by lazy { config.daoFactory.get<UserCredentials>() }

    private val contactsDao by lazy {
        CompoundDao(
            config.daoFactory.get<UserEmail>(),
            config.daoFactory.get<UserPhone>(),
        )
    }

    override fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult> = scope.later {
        val contact = contactsDao.all("value" isEqualTo rb.data.identifier).await().firstOrNull() ?: throw EntityNotFoundException("identifier", rb.data.identifier)
        val user = usersDao.load(contact.userId).await()
        val credentials = credentialsDao.all("userId" isEqualTo user.uid).await().first()
        if (credentials.credential != rb.data.password) throw RuntimeException("Incorrect password")
        SignInResult(user, user.spaces)
    }
}