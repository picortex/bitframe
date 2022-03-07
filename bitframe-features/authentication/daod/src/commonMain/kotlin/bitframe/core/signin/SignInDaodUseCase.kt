package bitframe.core.signin

import bitframe.core.*
import bitframe.core.exceptions.EntityNotFoundException
import bitframe.core.users.UserCredentials
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later

class SignInDaodUseCase(val config: ServiceConfigDaod) : SignInUseCase {
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
        val contact = contactsDao.all(UserContact::value isEqualTo rb.data.identifier).await().firstOrNull() ?: throw EntityNotFoundException("identifier", rb.data.identifier)
        val user = usersDao.load(contact.userId).await()
        val credentials = credentialsDao.all(UserCredentials::userId isEqualTo user.uid).await().first()
        if (credentials.credential != rb.data.password) throw RuntimeException("Incorrect password")
        val info = userSpaceInfoDao.all(UserSpaceInfo::userId isEqualTo user.uid).await()
        val spaces = buildList {
            for (infoX in info) add(spacesDao.load(infoX.spaceId))
        }.map { it.await() }
        // // Commended out coz it kept failing on JS about suspend function only in a suspend context
        // val space = info.map { spacesDao.load(it.spaceId) }.map { scope.async { it.await() }.await() }
        SignInResult(user, spaces.toInteroperableList())
    }
}