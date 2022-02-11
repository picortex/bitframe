@file:JsExport

package pimonitor.client.authentication.signup

import bitframe.service.client.config.ServiceConfig
import bitframe.service.requests.RequestBody
import later.Later
import later.await
import later.later
import pimonitor.authentication.signup.*
import pimonitor.authentication.signup.SignUpService
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class SignUpService(
    override val config: ServiceConfig
) : SignUpService(config), SignUpUseCase {

    @JsName("signUpAsBusiness")
    fun signUp(params: IRawBusinessSignUpParams) = signUp(params.toSignUpParams())

    @JsName("signUpAsIndividual")
    fun signUp(params: IRawIndividualSignUpParams) = signUp(params.toSignUpParams())

    @JsName("_ignore_signUp")
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