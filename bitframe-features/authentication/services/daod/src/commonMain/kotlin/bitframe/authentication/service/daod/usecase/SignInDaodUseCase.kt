package bitframe.authentication.service.daod.usecase

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.actors.users.UserEmail
import bitframe.actors.users.UserPhone
import bitframe.actors.users.UserSpaceInfo
import bitframe.authentication.signin.RawSignInCredentials
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.signin.SignInUseCase
import bitframe.authentication.users.UserCredentials
import bitframe.daos.CompoundDao
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.exceptions.EntityNotFoundException
import bitframe.daos.get
import bitframe.service.daod.config.DaodServiceConfig
import bitframe.service.requests.RequestBody
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later

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

    override fun signIn(rb: RequestBody.UnAuthorized<RawSignInCredentials>) = scope.later {
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