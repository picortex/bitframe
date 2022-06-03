@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.client.profile.ProfileService
import bitframe.client.signin.SignInService
import bitframe.client.signout.SignOutService
import kotlin.js.JsExport

@JsExport
interface BitframeApi {
//    val config: BitframeApiConfig

    //    val session: SessionAware
    val events: BitframeEvents

    //    val spaces: SpacesService
//    val users: UsersService
    val signIn: SignInService
    val profile: ProfileService
    val signOut: SignOutService
}