@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInIntent
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInViewModel
import useEventHandler

class SignInScope(service: SignInService) {

    val viewModel by lazy { SignInViewModel(service) }

    val submit = { cred: SignInCredentials ->
        viewModel.post(SignInIntent.Submit(cred.toSignInCredentials()))
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(service.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}