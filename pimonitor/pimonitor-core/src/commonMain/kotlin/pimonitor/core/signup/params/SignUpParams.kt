@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup.params

import bitframe.core.signin.SignInCredentials
import bitframe.core.users.RegisterUserParams
import kotlin.js.JsExport

@JsExport
sealed class SignUpParams {
    abstract fun toSignInCredentials(): SignInCredentials
    abstract fun toRegisterUserParams(): RegisterUserParams
}