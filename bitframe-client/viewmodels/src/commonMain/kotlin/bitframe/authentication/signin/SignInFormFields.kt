@file:JsExport

package bitframe.authentication.signin

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.TextInputField
import kotlin.js.JsExport

data class SignInFormFields(
    val title: String = "Sign In",
    val email: TextInputField = TextInputField("email", "john@doe.com"),
    val password: TextInputField = TextInputField("password", "<secure-password>"),
    val submit: ButtonInputField = ButtonInputField("Sign In")
) {
    companion object {
        internal fun with(credentials: SignInCredentials?) = SignInFormFields().apply {
            email.value = credentials?.alias
            password.value = credentials?.password
        }
    }
}