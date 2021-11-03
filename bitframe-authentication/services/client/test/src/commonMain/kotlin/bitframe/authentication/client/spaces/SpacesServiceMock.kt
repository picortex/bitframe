package bitframe.authentication.client.spaces

import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.authentication.spaces.SpacesService
import bitframe.daos.conditions.isEqualTo
import later.await
import later.later

class SpacesServiceMock(
    val config: SpacesServiceMockConfig,
) : SpacesService() {
    private val scope = config.scope
    private val usersDao = config.users
    private val spacesDao = config.spaces
    override fun register(params: RegisterSpaceParams) = scope.later {
        val user = usersDao.all("uid" isEqualTo params.userRef.uid).await().first()
        val space = spacesDao.createIfNotExist(CreateSpaceParams(params.name)).await()
        usersDao.update(user.copy(spaces = user.spaces + space))
        space
    }
}