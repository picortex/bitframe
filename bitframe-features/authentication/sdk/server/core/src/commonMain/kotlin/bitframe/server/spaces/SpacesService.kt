package bitframe.server.spaces

import bitframe.core.spaces.RegisterSpaceParams
import bitframe.core.spaces.SpacesService as CoreSpacesService
import bitframe.core.User
import bitframe.core.get
import bitframe.core.isEqualTo
import bitframe.server.ServiceConfig
import later.await
import later.later

class SpacesService(
    val config: ServiceConfig,
) : CoreSpacesService() {
    private val scope = config.scope
    private val usersDao = config.daoFactory.get<User>()
    override fun register(params: RegisterSpaceParams) = scope.later {
        val user = usersDao.all("uid" isEqualTo params.userRef.uid).await().first()
        TODO()
    }
}