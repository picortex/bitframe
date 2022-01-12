package bitframe.authentication.client.spaces

import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesService
import bitframe.service.client.config.ServiceConfig
import later.Later

class SpacesServiceMock(val config: ServiceConfig) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}