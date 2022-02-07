package bitframe.authentication.client.spaces

import bitframe.actors.spaces.RegisterSpaceParams
import bitframe.actors.spaces.Space
import bitframe.actors.spaces.SpacesService
import bitframe.service.client.config.KtorClientConfiguration
import later.Later

class SpacesServiceKtor(val config: KtorClientConfiguration) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}