package bitframe.client.spaces

import bitframe.core.spaces.RegisterSpaceParams
import bitframe.core.spaces.SpacesService
import bitframe.client.ServiceConfigKtor
import bitframe.core.Space
import later.Later

class SpacesServiceKtor(val config: ServiceConfigKtor) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}