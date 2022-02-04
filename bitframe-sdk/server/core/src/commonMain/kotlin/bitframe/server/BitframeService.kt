@file:JsExport

package bitframe.server

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.spaces.SpacesService
import bitframe.authentication.server.users.UsersService
import bitframe.service.server.config.ServiceConfig
import kotlin.js.JsExport

open class BitframeService(config: ServiceConfig) {
    val spaces: SpacesService = SpacesService(config)
    val users: UsersService = UsersService(config)
    val signIn: SignInService = SignInService(config)
}