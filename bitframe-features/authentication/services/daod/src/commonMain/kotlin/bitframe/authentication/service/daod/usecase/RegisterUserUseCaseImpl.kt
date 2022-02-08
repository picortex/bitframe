package bitframe.authentication.service.daod.usecase

import bitframe.actors.modal.Identifier
import bitframe.actors.spaces.Space
import bitframe.actors.users.*
import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.service.daod.exceptions.UserFoundException
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.users.UserCredentials
import bitframe.daos.CompoundDao
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.daod.config.DaodServiceConfig
import kotlinx.collections.interoperable.listOf
import kotlinx.datetime.Clock
import later.Later
import later.await
import later.later
import validation.validate

class RegisterUserUseCaseImpl(
    private val config: DaodServiceConfig
) : RegisterUserUseCase {
    private val usersDao by lazy { config.daoFactory.get<User>() }
    private val spacesDao by lazy { config.daoFactory.get<Space>() }
    private val contactsDao by lazy {
        CompoundDao(
            config.daoFactory.get<UserPhone>(),
            config.daoFactory.get<UserEmail>(),
        )
    }
    private val credentialsDao by lazy { config.daoFactory.get<UserCredentials>() }

    fun validate(params: RegisterUserParams) = validate {
        Identifier.from(params.userIdentifier)
        params
    }

    override fun register(params: RegisterUserParams): Later<SignInResult> = config.scope.later {
        val userParams = validate(params).getOrThrow()
        val registered = contactsDao.all("value" isEqualTo params.userIdentifier).await()
        if (registered.isNotEmpty()) throw UserFoundException(params.userIdentifier)

        val userIntermediateOutput = usersDao.create(params.toUserInput()).await()

        val credentialsInput = UserCredentials(
            userId = userIntermediateOutput.uid,
            credential = params.userPassword
        )

        val credentialsOutput = credentialsDao.create(credentialsInput)

        val contactsInput = UserContact.of(
            value = userParams.userIdentifier,
            userId = userIntermediateOutput.uid,
            userRef = userIntermediateOutput.ref()
        )

        val contactsOutput = contactsDao.create(contactsInput)

        val contacts = contactsOutput.await();

        val spaceOutput = spacesDao.create(params.toSpaceInput()).await()

        credentialsOutput.await();

        val userFinalOutput = usersDao.update(
            userIntermediateOutput.copy(
                spaces = listOf(spaceOutput),
                contacts = listOf(contacts),
                lastSeen = Clock.System.now().toEpochMilliseconds()
            )
        ).await()
        SignInResult(userFinalOutput, userFinalOutput.spaces)
    }
}