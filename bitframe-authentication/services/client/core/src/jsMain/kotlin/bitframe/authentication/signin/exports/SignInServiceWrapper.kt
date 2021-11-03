@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.authentication.client.signin.SignInService

class SignInServiceWrapper(service: SignInService) {
    val signIn = { credentials: SignInCredentials ->
        service.signIn(credentials.toSignInCredentials())
    }
}