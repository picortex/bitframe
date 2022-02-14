package bitframe.authentication.service.daod.usecase

import bitframe.core.actors.spaces.Space
import bitframe.core.User
import bitframe.core.UserEmail
import bitframe.core.UserPhone
import bitframe.core.actors.users.UserSpaceInfo
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.signin.SignInUseCase
import bitframe.authentication.users.UserCredentials
import bitframe.core.daos.CompoundDao
import bitframe.core.daos.conditions.isEqualTo
import bitframe.core.exceptions.EntityNotFoundException
import bitframe.core.DaodServiceConfig
import bitframe.core.service.requests.RequestBody

class SignInDaodUseCase(val config: DaodServiceConfig) : SignInUseCase {
    private val scope get() = config.scope
    private val usersDao by lazy { config.daoFactory.get<User>() }
    private val spacesDao by lazy { config.daoFactory.get<Space>() }
    private val userSpaceInfoDao by lazy { config.daoFactory.get<UserSpaceInfo>() }
    private val credentialsDao by lazy { config.daoFactory.get<UserCredentials>() }

    private val contactsDao by lazy {
        CompoundDao(
            config.daoFactory.get<UserEmail>(),
            config.daoFactory.get<UserPhone>(),
        )
    }

    override fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>) = scope.later {
        val contact = contactsDao.all("value" isEqualTo rb.data.identifier).await().firstOrNull() ?: throw EntityNotFoundException("identifier", rb.data.identifier)
        val user = usersDao.load(contact.userId).await()
        val credentials = credentialsDao.all(UserCredentials::userId isEqualTo user.uid).await().first()
        if (credentials.credential != rb.data.password) throw RuntimeException("Incorrect password")
        val info = userSpaceInfoDao.all("userId" isEqualTo user.uid).await()
        val spaces = buildList {
            for (infoX in info) add(spacesDao.load(infoX.spaceId))
        }.map { it.await() }
        // // Commended out coz it kept failing on JS about suspend function only in a suspend context
        // val space = info.map { spacesDao.load(it.spaceId) }.map { scope.async { it.await() }.await() }
        SignInResult(user, spaces.toInteroperableList())
    }
}