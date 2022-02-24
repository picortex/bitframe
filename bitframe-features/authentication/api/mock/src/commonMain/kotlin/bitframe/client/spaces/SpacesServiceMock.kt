package bitframe.client.spaces

import bitframe.core.spaces.RegisterSpaceParams
import bitframe.core.spaces.SpacesService
import bitframe.client.ServiceConfig
import bitframe.core.Space
import later.Later

class SpacesServiceMock(val config: ServiceConfig) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}