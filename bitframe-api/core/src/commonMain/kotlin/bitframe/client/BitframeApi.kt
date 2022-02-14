@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import bitframe.client.signin.SignInService
import bitframe.client.signout.SignOutService
import bitframe.core.spaces.SpacesService
import bitframe.core.users.UsersService
import kotlin.js.JsExport

interface BitframeApi {
    val config: BitframeApiConfig
    val session: SessionAware
    val spaces: SpacesService
    val users: UsersService
    val signIn: SignInService
    val signOut: SignOutService
}