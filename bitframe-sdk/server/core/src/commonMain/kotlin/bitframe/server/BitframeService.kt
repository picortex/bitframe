@file:JsExport

package bitframe.server

import bitframe.core.actors.spaces.Space
import bitframe.core.User
import bitframe.server.signin.SignInService
import bitframe.server.spaces.SpacesService
import bitframe.service.server.GenericService
import kotlin.js.JsExport

open class BitframeService(open val config: ServiceConfig) {
    val spaces: SpacesService by lazy { SpacesService(config) }
    val genericUsers: GenericService<User> by lazy { GenericService(config) }
    val genericSpaces: GenericService<Space> by lazy { GenericService(config) }
    val signIn: SignInService by lazy { SignInService(config) }
}