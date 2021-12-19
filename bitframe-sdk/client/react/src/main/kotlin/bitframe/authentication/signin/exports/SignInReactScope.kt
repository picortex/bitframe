@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signin.exports

import bitframe.api.BitframeService
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.*
import bitframe.client.ReactUIScope
import useEventHandler
import bitframe.authentication.signin.SignInIntent as Intent
import bitframe.authentication.signin.SignInState as State

class SignInReactScope(service: BitframeService) : SignInScope(service), ReactUIScope<Intent, State> {

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(service.signIn.config.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}