@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.authentication.signin.SignInIntent
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInViewModel

class SignInScope(service: SignInService) {

    val viewModel by lazy { SignInViewModel(service) }

    val submit = { cred: SignInCredentials ->
        viewModel.post(SignInIntent.Submit(cred.toSignInCredentials()))
    }
}