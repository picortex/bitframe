@file:JsExport

package bitframe.server

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.spaces.SpacesService
import bitframe.authentication.server.users.UsersService
import bitframe.service.config.ServiceConfig
import kotlin.js.JsExport

abstract class BitframeService(open val config: ServiceConfig) {
    abstract val spaces: SpacesService
    abstract val users: UsersService
    abstract val signIn: SignInService
}