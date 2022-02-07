package bitframe.authentication.server.users.usecases

import bitframe.authentication.server.spaces.usecases.CreateSpaceIfNotExist
import bitframe.authentication.signin.Basic
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.CreateUserParams
import bitframe.actors.users.RegisterUserParams
import bitframe.actors.users.User
import bitframe.authentication.users.toUser
import bitframe.actors.users.usecases.RegisterUser
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.listOf
import later.Later
import later.await
import later.later

class RegisterUserImpl(
    private val config: ServiceConfig
) : RegisterUser, CreateSpaceIfNotExist by CreateSpaceIfNotExist(config) {

    override fun registerWithSpace(
        user: RegisterUserParams,
        space: CreateSpaceParams
    ): Later<LoginConundrum> = config.scope.later {
        val usersDao = config.daoFactory.get<User>()
        val userParams = CreateUserParams(
            name = user.name,
            contacts = user.contacts,
            credentials = Basic(
                identity = user.contacts.firstValue(),
                password = user.password
            )
        )
        val spaceParams = CreateSpaceParams(space.name)
        val s = createSpaceIfNotExist(spaceParams).await()
        val u = usersDao.create(userParams.toUser("")).await()
        val usr = usersDao.update(u.copy(spaces = listOf(s))).await()
        LoginConundrum(usr, usr.spaces)
    }
}