@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package authenticator

import bitframe.client.ServiceConfig
import bitframe.client.signin.SignInService
import bitframe.client.signout.SignOutService
import kotlin.js.JsExport

class AuthenticatorServices(
    val config: ServiceConfig,
    val signIn: SignInService,
    val signOut: SignOutService,
    val users: UsersService
)