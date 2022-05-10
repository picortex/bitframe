@file:JsExport

package bitframe.client.signin

import bitframe.core.Space
import bitframe.core.signin.SignInRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport

class SignInIntents internal constructor(
    private val viewModel: ViewModel<SignInIntent, *>
) {
    val initializeForm = { viewModel.post(SignInIntent.InitForm) }

    val submitCredentials = { cred: SignInRawParams ->
        viewModel.post(SignInIntent.Submit(cred))
    }

    val resolveConundrum = { space: Space ->
        viewModel.post(SignInIntent.ResolveConundrum(space))
    }
}