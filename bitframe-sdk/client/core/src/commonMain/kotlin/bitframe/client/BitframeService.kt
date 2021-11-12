@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import kotlin.js.JsExport

interface BitframeService {
    val spaces: SpacesService
    val users: UsersService
    val signIn: SignInService
}