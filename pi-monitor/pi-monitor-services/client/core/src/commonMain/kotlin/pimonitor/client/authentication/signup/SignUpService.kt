@file:JsExport

package pimonitor.client.authentication.signup

import bitframe.service.client.config.ServiceConfig
import bitframe.service.requests.RequestBody
import later.Later
import later.await
import later.later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.SignUpUseCase
import kotlin.js.JsExport

abstract class SignUpService(
    override val config: ServiceConfig
) : SignUpService(config), SignUpUseCase {

    fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val request = RequestBody.UnAuthorized(
            appId = config.appId,
            data = validate(params).getOrThrow()
        )
        val result = signUp(request).await()
        bus.dispatch(signUpEvent(result))
        result
    }
}