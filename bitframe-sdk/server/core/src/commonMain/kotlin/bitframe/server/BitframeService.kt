@file:JsExport

package bitframe.server

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.spaces.SpacesService
import bitframe.authentication.server.users.UsersService
import bitframe.service.server.GenericService
import bitframe.service.server.config.ServiceConfig
import kotlin.js.JsExport

open class BitframeService(open val config: ServiceConfig) {
    val spaces: SpacesService by lazy { SpacesService(config) }
    val users: UsersService by lazy { UsersService(config) }
    val genericUsers: GenericService<User> by lazy { GenericService(config) }
    val genericSpaces: GenericService<Space> by lazy { GenericService(config) }
    val signIn: SignInService by lazy { SignInService(config) }
}