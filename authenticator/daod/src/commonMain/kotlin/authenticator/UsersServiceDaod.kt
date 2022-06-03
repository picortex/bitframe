package authenticator

import bitframe.core.*
import bitframe.core.exceptions.UserFoundException
import bitframe.core.signin.SignInResult
import bitframe.core.users.RegisterUserParams
import bitframe.core.users.UserCredentials
import bitframe.core.users.toSpaceInput
import bitframe.core.users.toUserInput
import kotlinx.collections.interoperable.listOf
import later.Later
import later.await
import later.later

class UsersServiceDaod(
    val config: ServiceConfigDaod
) : UsersServiceCore {
    private val factory get() = config.daoFactory
    private val usersDao by lazy { factory.get<User>() }
    private val spacesDao by lazy { factory.get<Space>() }
    private val userSpaceInfoDao by lazy { factory.get<UserSpaceInfo>() }
    private val contactsDao by lazy { CompoundDao(factory.get<UserPhone>(), factory.get<UserEmail>()) }
    private val credentialsDao by lazy { factory.get<UserCredentials>() }

    fun validate(params: RegisterUserParams) = validation.validate {
        Identifier.from(params.userIdentifier)
        params
    }

    override fun register(rb: RequestBody.UnAuthorized<RegisterUserParams>): Later<SignInResult> = config.scope.later {
        val logger by config.logger(rb, config.logger)
        logger.info("Registering user ${rb.data.userName}")
        val input = validate(rb.data).getOrThrow()
        val registered = contactsDao.all(UserContact::value isEqualTo input.userIdentifier).await()
        if (registered.isNotEmpty()) throw UserFoundException(input.userIdentifier)

        val userOutput = usersDao.create(input.toUserInput()).await()
        val spaceOutput = spacesDao.create(input.toSpaceInput()).await()

        val infoInput = UserSpaceInfo(
            userId = userOutput.uid,
            spaceId = spaceOutput.uid,
            type = input.userType
        )

        userSpaceInfoDao.create(infoInput).await()

        val credentialsInput = UserCredentials(
            userId = userOutput.uid,
            credential = input.userPassword
        )

        val credentialsOutput = credentialsDao.create(credentialsInput)

        val contactsInput = UserContact.of(
            value = input.userIdentifier,
            userId = userOutput.uid,
        )

        contactsDao.create(contactsInput).await()

        credentialsOutput.await();

        SignInResult(userOutput, listOf(spaceOutput)).also {
            logger.info("${rb.data.userName}'s registration completed successfully")
        }
    }
}