package bitframe.authentication.client.spaces

import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesService
import bitframe.service.client.config.KtorClientConfiguration
import later.Later

class SpacesServiceKtor(val configuration: KtorClientConfiguration) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}