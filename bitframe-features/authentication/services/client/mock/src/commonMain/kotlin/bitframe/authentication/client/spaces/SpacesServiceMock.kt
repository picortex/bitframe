package bitframe.authentication.client.spaces

import bitframe.core.actors.spaces.RegisterSpaceParams
import bitframe.core.actors.spaces.Space
import bitframe.core.actors.spaces.SpacesService
import bitframe.client.ServiceConfig
import later.Later

class SpacesServiceMock(val config: ServiceConfig) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}