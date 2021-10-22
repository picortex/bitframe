@file:JsExport

package pimonitor.authentication.signup

import bitframe.events.Event
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import contacts.Email
import later.Later
import later.await
import later.later
import kotlin.js.JsExport

abstract class SignUpService(
    protected open val bus: EventBus,
    protected open val config: ServiceConfig
) {
    protected val scope get() = config.scope

    companion object {
        const val SIGN_UP_EVENT_ID = "pimonitor.authentication.signup"
        fun signUpEvent(data: SignUpResult) = Event(SIGN_UP_EVENT_ID, data)
    }

    fun validate(params: SignUpParams) = when (params) {
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

    protected abstract fun executeSignUp(params: SignUpParams): Later<SignUpResult>

    fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        validate(params)
        val result = executeSignUp(params).await()
        bus.dispatch(signUpEvent(result))
        result
    }
}