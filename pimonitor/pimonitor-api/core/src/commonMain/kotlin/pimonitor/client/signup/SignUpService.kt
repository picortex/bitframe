@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import later.Later
import later.await
import later.later
import logging.Logger
import pimonitor.core.signup.*
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.signup.SignUpService as CoreSignUpService

abstract class SignUpService(
    override val config: ServiceConfig
) : CoreSignUpService(config), SignUpUseCase {

    override val logger get() = config.logger.with("source" to this::class.simpleName)

    @JsName("signUpAsBusiness")
    fun signUp(params: IRawBusinessSignUpParams) = signUp(params.toSignUpParams())

    @JsName("signUpAsIndividual")
    fun signUp(params: IRawIndividualSignUpParams) = signUp(params.toSignUpParams())

    @JsName("_ignore_signUp")
    fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        logger.info("Registering ${params.entity}")
        val request = RequestBody.UnAuthorized(
            appId = config.appId,
            data = validate(params).getOrThrow()
        )
        val result = signUp(request).await()
        bus.dispatch(signUpEvent(result))
        logger.info("Registered ${params.entity}")
        result
    }
}