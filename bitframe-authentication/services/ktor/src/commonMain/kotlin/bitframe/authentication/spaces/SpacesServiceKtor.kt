package bitframe.authentication.spaces

import bitframe.service.config.KtorClientConfiguration
import later.Later

class SpacesServiceKtor(val configuration: KtorClientConfiguration) : SpacesService() {
    override fun register(params: RegisterSpaceParams): Later<Space> {
        TODO("Not yet implemented")
    }
}