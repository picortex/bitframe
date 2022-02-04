package bitframe.authentication.server.spaces.usecases

import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.service.server.config.ServiceConfig
import later.Later

interface CreateSpaceIfNotExist {
    companion object {
        operator fun invoke(config: ServiceConfig): CreateSpaceIfNotExist = CreateSpaceIfNotExistImpl(config)
    }

    fun createSpaceIfNotExist(params: CreateSpaceParams): Later<Space>
}