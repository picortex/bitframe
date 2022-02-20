@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup.params

import bitframe.core.signin.SignInCredentials
import bitframe.core.users.RegisterUserParams
import kotlin.js.JsExport

sealed interface SignUpRawParams {
    fun toSignInCredentials(): SignInCredentials
    fun toRegisterUserParams(): RegisterUserParams
}