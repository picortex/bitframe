package bitframe.authentication.client.spaces

import bitframe.actors.spaces.RegisterSpaceParams
import bitframe.actors.spaces.SpacesService
import bitframe.service.client.config.ServiceConfig
import later.Later

class SpacesServiceMock(val config: ServiceConfig) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}