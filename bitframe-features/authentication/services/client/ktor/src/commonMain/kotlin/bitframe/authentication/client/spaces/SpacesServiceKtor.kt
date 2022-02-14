package bitframe.authentication.client.spaces

import bitframe.core.actors.spaces.RegisterSpaceParams
import bitframe.core.actors.spaces.Space
import bitframe.core.actors.spaces.SpacesService
import bitframe.client.KtorServiceConfig
import later.Later

class SpacesServiceKtor(val config: KtorServiceConfig) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}