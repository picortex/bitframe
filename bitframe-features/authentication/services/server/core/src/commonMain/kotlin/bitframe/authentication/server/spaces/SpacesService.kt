package bitframe.authentication.server.spaces

import bitframe.authentication.server.spaces.usecases.CreateSpaceIfNotExist
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.actors.spaces.RegisterSpaceParams
import bitframe.actors.spaces.SpacesService
import bitframe.actors.users.User
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.toInteroperableList
import later.await
import later.later

class SpacesService(
    val config: ServiceConfig,
) : SpacesService(), CreateSpaceIfNotExist by CreateSpaceIfNotExist(config) {
    private val scope = config.scope
    private val usersDao = config.daoFactory.get<User>()
    override fun register(params: RegisterSpaceParams) = scope.later {
        val user = usersDao.all("uid" isEqualTo params.userRef.uid).await().first()
        val space = createSpaceIfNotExist(CreateSpaceParams(params.name, params.name, params.name)).await()
        usersDao.update(user.copy(spaces = (user.spaces + space).toInteroperableList()))
        space
    }
}