@file:JsExport

package bitframe.client.signin

import bitframe.core.signin.SignInCredentials
import presenters.fields.ButtonInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport

data class SignInFormFields(
    val title: String = "Log in to your space",
    val email: TextInputField = TextInputField("email", "Enter email address"),
    val password: TextInputField = TextInputField("password", "Password"),
    val recoverText: String = "Forgot password?",
    val signUpPromptText: String = "New to PiMonitor? Sign Up",
    val submit: ButtonInputField = ButtonInputField("Sign In")
) {
    companion object {
        internal fun with(credentials: SignInCredentials?) = SignInFormFields().apply {
            email.value = credentials?.email
            password.value = credentials?.password
        }
    }
}