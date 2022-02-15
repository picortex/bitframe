@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import later.Later
import later.await
import later.later
import pimonitor.core.signup.*
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.signup.SignUpService as CoreSignUpService

@JsExport
abstract class SignUpService(
    override val config: ServiceConfig
) : CoreSignUpService(config), SignUpUseCase {

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