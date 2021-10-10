@file:JsExport

package pimonitor.authentication.signup

import bitframe.authentication.signin.LoginConundrum
import contacts.Email
import later.Later
import pimonitor.Monitor
import pimonitor.monitors.SignUpParams
import kotlin.js.JsExport

abstract class SignUpService {
    fun validate(params: SignUpParams) {
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
    }
    
    abstract fun signUp(params: SignUpParams): Later<SignUpResult>
}