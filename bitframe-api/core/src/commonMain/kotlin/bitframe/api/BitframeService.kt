@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.api

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import kotlin.js.JsExport

interface BitframeService {
    val config: BitframeServiceConfig
    val spaces: SpacesService
    val users: UsersService
    val signIn: SignInService
}