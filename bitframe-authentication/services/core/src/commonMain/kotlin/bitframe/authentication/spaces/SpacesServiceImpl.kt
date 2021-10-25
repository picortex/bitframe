package bitframe.authentication.spaces

import bitframe.authentication.users.UsersDao
import bitframe.daos.conditions.isEqualTo
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later

class SpacesServiceImpl(
    val config: ServiceConfig,
    val spacesDao: SpacesDao,
    val usersDao: UsersDao
) : SpacesService() {
    private val scope = config.scope
    override fun register(params: RegisterSpaceParams) = scope.later {
        val user = usersDao.all("uid" isEqualTo params.userRef.uid).await().first()
        val space = spacesDao.createIfNotExist(CreateSpaceParams(params.name)).await()
        usersDao.update(user.copy(spaces = user.spaces + space))
        space
    }
}