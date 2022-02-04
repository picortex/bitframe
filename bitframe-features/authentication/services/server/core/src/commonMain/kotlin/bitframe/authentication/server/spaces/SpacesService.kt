package bitframe.authentication.server.spaces

import bitframe.authentication.spaces.*
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.User
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later

class SpacesService(
    val config: ServiceConfig,
) : SpacesService() {
    private val scope = config.scope
    private val usersDao = config.daoFactory.get<User>()
    private val spacesDao = config.daoFactory.get<Space>()
    override fun register(params: RegisterSpaceParams) = scope.later {
        val user = usersDao.all("uid" isEqualTo params.userRef.uid).await().first()
        val space = createSpaceIfNotExist(CreateSpaceParams(params.name)).await()
        usersDao.update(user.copy(spaces = (user.spaces + space).toInteroperableList()))
        space
    }

    fun createSpaceIfNotExist(params: CreateSpaceParams): Later<Space> = scope.later {
        val existing = spacesDao.all(condition = "name" isEqualTo params.name).await().firstOrNull()
        existing ?: spacesDao.create(params.toSpace("")).await()
    }
}