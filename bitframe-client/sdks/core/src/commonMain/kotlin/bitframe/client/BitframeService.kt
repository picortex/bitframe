@file:JsExport

package bitframe.client

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import kotlin.js.JsExport

abstract class BitframeService(open val config: BitframeServiceConfig) {
    abstract val spaces: SpacesService
    abstract val users: UsersService
    abstract val signIn: SignInService
}