@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signin

import presenters.fields.TextInputField
import kotlin.js.JsExport
import kotlin.js.JsName
import bitframe.core.signin.SignInRawParams as Params

class SignInFields(
    email: String? = null,
    password: String? = null
) {
    @JsName("fromParams")
    constructor(cred: Params) : this(
        email = cred.email ?: cred.identifier,
        password = cred.password
    )

    val email = TextInputField(
        name = Params::email,
        label = "Enter email address",
        hint = "john@doe.com",
        value = email
    )
    val password = TextInputField(
        name = Params::password,
        label = "Password",
        hint = "Secure Password",
        value = password
    )
    val recoverText: String = "Forgot password?"
    val signUpPromptText: String = "New to PiMonitor? Sign Up"
}