@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ServiceConfig
import bitframe.client.logger
import bitframe.core.RequestBody
import bitframe.core.logger
import later.await
import later.later
import logging.Logger
import pimonitor.core.signup.*
import pimonitor.core.signup.params.BusinessSignUpRawParams
import pimonitor.core.signup.params.IndividualSignUpRawParams
import pimonitor.core.signup.params.toBusinessSignUpParams
import pimonitor.core.signup.params.toIndividualSignUpParams
import kotlin.js.JsExport
import kotlin.js.JsName
import pimonitor.core.signup.SignUpService as CoreSignUpService

interface SignUpService : CoreSignUpService {
    val config: ServiceConfig
    val logger get() = config.logger

    @JsName("signUpAsBusiness")
    fun signUp(params: BusinessSignUpRawParams) = config.scope.later {
        logger.info("Registering ${params.businessName} as business")
        signUpAsBusiness(
            RequestBody.UnAuthorized(
                appId = config.appId,
                data = params.toBusinessSignUpParams()
            )
        ).await().finalize()
    }

    @JsName("signUpAsIndividual")
    fun signUp(params: IndividualSignUpRawParams) = config.scope.later {
        logger.info("Registering ${params.name} as individual")
        signUpAsIndividual(
            RequestBody.UnAuthorized(
                appId = config.appId,
                data = params.toIndividualSignUpParams()
            )
        ).await().finalize()
    }

    fun SignUpResult.finalize(): SignUpResult {
        config.bus.dispatch(SignUpEvent(this))
        logger.info("Registration completed successfully")
        return this
    }
}