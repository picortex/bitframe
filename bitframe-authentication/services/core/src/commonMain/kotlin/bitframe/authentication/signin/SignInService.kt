@file:JsExport

package bitframe.authentication.signin

import later.Later
import kotlin.js.JsExport

abstract class SignInService {
    abstract fun signIn(credentials: SignInCredentials): Later<LoginConundrum>
}