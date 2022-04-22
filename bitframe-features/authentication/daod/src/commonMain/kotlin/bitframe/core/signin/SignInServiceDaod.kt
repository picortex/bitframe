package bitframe.core.signin

import bitframe.core.*
import bitframe.core.exceptions.EntityNotFoundException
import bitframe.core.users.UserCredentials
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later

class SignInServiceDaod(val config: ServiceConfigDaod) : SignInServiceCore {
    private val scope get() = config.scope
    private val factory get() = config.daoFactory
    private val usersDao by lazy { factory.get<User>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val credentialsDao by lazy { factory.get<UserCredentials>() }
    private val contactsDao by lazy { CompoundDao(factory.get<UserEmail>(), factory.get<UserPhone>()) }

    override fun signIn(rb: RequestBody.UnAuthorized<SignInParams>) = scope.later {
        val params = rb.data.toSignInParams()
        val contact = contactsDao.all(UserContact::value isEqualTo params.identifier).await().firstOrNull() ?: throw EntityNotFoundException("identifier", params.identifier)
        val user = usersDao.load(contact.userId).await()
        val credentials = credentialsDao.all(UserCredentials::userId isEqualTo user.uid).await().first()
        if (credentials.credential != params.password) throw RuntimeException("Incorrect password")
        val info = userSpaceInfoDao.all(UserSpaceInfo::userId isEqualTo user.uid).await()
        val spaces = buildList {
            for (infoX in info) add(spacesDao.load(infoX.spaceId))
        }.map { it.await() }
        // // Commended out coz it kept failing on JS about suspend function only in a suspend context
        // val space = info.map { spacesDao.load(it.spaceId) }.map { scope.async { it.await() }.await() }
        SignInResult(user, spaces.toInteroperableList())
    }
}