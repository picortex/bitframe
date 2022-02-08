package bitframe.authentication.server.users.usecases

import bitframe.actors.spaces.Space
import bitframe.actors.users.*
import bitframe.authentication.server.spaces.usecases.CreateSpaceIfNotExist
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.actors.users.usecases.RegisterUser
import bitframe.authentication.server.users.UserFoundException
import bitframe.actors.modal.Identifier
import bitframe.authentication.users.UserCredentials
import bitframe.daos.CompoundDao
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import identifier.Name
import kotlinx.collections.interoperable.listOf
import kotlinx.datetime.Clock
import later.Later
import later.await
import later.later
import validation.validate

class RegisterUserImpl(
    private val config: ServiceConfig
) : RegisterUser, CreateSpaceIfNotExist by CreateSpaceIfNotExist(config) {

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
        Identifier.from(params.identifier)
        params
    }

    override fun register(
        user: RegisterUserParams,
        space: CreateSpaceParams
    ): Later<SignInResult> = config.scope.later {
        val userParams = validate(user).getOrThrow()
        val registered = contactsDao.all("value" isEqualTo user.identifier).await()
        if (registered.isNotEmpty()) throw UserFoundException(user.identifier)

        val userInput = User(
            name = user.name,
            tag = Name(user.name).first,
            contacts = listOf(),
            spaces = listOf()
        )

        val userIntermediateOutput = usersDao.create(userInput).await()
        println(userIntermediateOutput)

        val credentialsInput = UserCredentials(
            userId = userIntermediateOutput.uid,
            credential = user.password
        )

        val credentialsOutput = credentialsDao.create(credentialsInput)

        val contactsInput = UserContact.of(
            value = userParams.identifier,
            userId = userIntermediateOutput.uid,
            userRef = userIntermediateOutput.ref()
        )

        val contactsOutput = contactsDao.create(contactsInput)

        val spaceInput = Space(
            name = space.name,
            scope = space.scope,
            type = space.type
        )

        val contacts = contactsOutput.await();
        val spaceOutput = spacesDao.create(spaceInput).await()
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