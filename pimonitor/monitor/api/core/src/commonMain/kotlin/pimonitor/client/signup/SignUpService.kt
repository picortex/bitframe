@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ServiceConfig
import bitframe.client.logger
import bitframe.core.RequestBody
import later.await
import later.later
import pimonitor.client.events.SignedUpEvent
import pimonitor.core.signup.*
import pimonitor.core.signup.params.SignUpBusinessRawParams
import pimonitor.core.signup.params.SignUpIndividualRawParams
import pimonitor.core.signup.params.toValidatedSignUpParams
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.signup.SignUpServiceCore as CoreSignUpService

abstract class SignUpService(
    private val config: ServiceConfig
) : CoreSignUpService {
    private val logger by config.logger(withSessionInfo = true)

    @JsName("signUpAsBusiness")
    fun signUp(params: SignUpBusinessRawParams) = config.scope.later {
        logger.info("Registering ${params.businessName} as business")
        signUpAsBusiness(
            RequestBody.UnAuthorized(
                appId = config.appId,
                data = params.toValidatedSignUpParams()
            )
        ).await().finalize()
    }

    @JsName("signUpAsIndividual")
    fun signUp(params: SignUpIndividualRawParams) = config.scope.later {
        logger.info("Registering ${params.name} as individual")
        signUpAsIndividual(
            RequestBody.UnAuthorized(
                appId = config.appId,
                data = params.toValidatedSignUpParams()
            )
        ).await().finalize()
    }

    private fun SignUpResult.finalize(): SignUpResult {
        config.bus.dispatch(SignedUpEvent(this))
        logger.info("Registration completed successfully")
        return this
    }
}