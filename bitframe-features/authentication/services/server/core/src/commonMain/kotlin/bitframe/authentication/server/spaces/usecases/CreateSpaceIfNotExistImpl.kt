package bitframe.authentication.server.spaces.usecases

import bitframe.actors.spaces.Space
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.toSpace
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import later.Later
import later.await
import later.later

internal class CreateSpaceIfNotExistImpl(
    private val config: ServiceConfig
) : CreateSpaceIfNotExist {
    override fun createSpaceIfNotExist(params: CreateSpaceParams): Later<Space> = config.scope.later {
        val spacesDao = config.daoFactory.get<Space>()
        val existing = spacesDao.all(condition = "name" isEqualTo params.name).await().firstOrNull()
        existing ?: spacesDao.create(params.toSpace("")).await()
    }
}