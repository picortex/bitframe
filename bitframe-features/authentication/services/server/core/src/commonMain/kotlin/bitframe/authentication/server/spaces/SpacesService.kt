package bitframe.authentication.server.spaces

import bitframe.actors.spaces.RegisterSpaceParams
import bitframe.actors.spaces.SpacesService
import bitframe.actors.users.User
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import later.await
import later.later

class SpacesService(
    val config: ServiceConfig,
) : SpacesService() {
    private val scope = config.scope
    private val usersDao = config.daoFactory.get<User>()
    override fun register(params: RegisterSpaceParams) = scope.later {
        val user = usersDao.all("uid" isEqualTo params.userRef.uid).await().first()
        TODO()
    }
}