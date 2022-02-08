@file:JsExport

package pimonitor.authentication.signup

import bitframe.service.config.ServiceConfig
import bitframe.service.requests.RequestBody
import events.Event
import identifier.Email
import later.Later
import validation.validate
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class SignUpService(
    open val config: ServiceConfig
) : SignUpUseCase {
    protected val scope get() = config.scope
    protected val bus get() = config.bus

    companion object {
        const val SIGN_UP_EVENT_TOPIC = "pimonitor.authentication.signup"
        fun signUpEvent(data: SignUpResult) = Event(data, SIGN_UP_EVENT_TOPIC)
    }

    @JsName("validateIndividualSignUpParams")
    fun validate(params: SignUpParams.Individual) = validate {
        require(params.name.isNotEmpty()) {
            "Name must not be empty"
        }
        Email(params.email)
        require(params.password.isNotEmpty()) {
            "Password must not be empty"
        }
        params
    }

    @JsName("validateBusinessSignUpParams")
    fun validate(params: SignUpParams.Business) = validate {
        require(params.businessName.isNotEmpty() && params.individualName.isNotEmpty()) {
            "Name must not be empty"
        }
        Email(params.individualEmail)
        require(params.password.isNotEmpty()) {
            "Password must not be empty"
        }
        params
    }

    @JsName("validateSignUpParams")
    fun validate(params: SignUpParams) = when (params) {
        is SignUpParams.Individual -> validate(params)
        is SignUpParams.Business -> validate(params)
    }
}