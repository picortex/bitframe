@file:JsExport

package pimonitor.authentication.signup

import bitframe.events.Event
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import contacts.Email
import later.Later
import later.await
import later.later
import validation.Validation
import validation.validate
import kotlin.js.JsExport

abstract class SignUpService(
    open val config: ServiceConfig
) {
    private val scope get() = config.scope
    private val bus get() = config.bus

    companion object {
        const val SIGN_UP_EVENT_ID = "pimonitor.authentication.signup"
        fun signUpEvent(data: SignUpResult) = Event(SIGN_UP_EVENT_ID, data)
    }

    fun validate(params: SignUpParams): Validation<SignUpParams> = validate {
        when (params) {
            is SignUpParams.Individual -> {
                require(params.name.isNotEmpty()) {
                    "Name must not be empty"
                }
                Email(params.email)
                require(params.password.isNotEmpty()) {
                    "Password must not be empty"
                }
            }
            is SignUpParams.Business -> {
                require(params.businessName.isNotEmpty() && params.individualName.isNotEmpty()) {
                    "Name must not be empty"
                }
                Email(params.individualEmail)
                require(params.password.isNotEmpty()) {
                    "Password must not be empty"
                }
            }
        }
        params
    }

    protected abstract fun executeSignUp(params: SignUpParams): Later<SignUpResult>

    fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val result = executeSignUp(validate(params).getOrThrow()).await()
        bus.dispatch(signUpEvent(result))
        result
    }
}