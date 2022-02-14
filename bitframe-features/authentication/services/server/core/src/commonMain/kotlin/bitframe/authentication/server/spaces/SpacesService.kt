package bitframe.authentication.server.spaces

import bitframe.core.actors.spaces.RegisterSpaceParams
import bitframe.core.actors.spaces.SpacesService
import bitframe.core.User
import bitframe.core.daos.conditions.isEqualTo
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