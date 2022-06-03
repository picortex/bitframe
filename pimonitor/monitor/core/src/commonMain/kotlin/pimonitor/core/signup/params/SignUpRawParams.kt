package pimonitor.core.signup.params

import bitframe.core.signin.SignInParams
import bitframe.core.users.RegisterUserParams
import kotlin.js.JsExport

@JsExport
sealed interface SignUpRawParams {
    val password: String
}

fun SignUpRawParams.toSignInParams(): SignInParams = when (this) {
    is SignUpBusinessRawParams -> toSignInParams()
    is SignUpIndividualRawParams -> toSignInParams()
}

fun SignUpRawParams.toRegisterUserParams(): RegisterUserParams = when (this) {
    is SignUpBusinessRawParams -> toRegisterUserParams()
    is SignUpIndividualRawParams -> toRegisterUserParams()
}

fun SignUpRawParams.toValidatedSignUpParams(): SignUpRawParams = when (this) {
    is SignUpBusinessRawParams -> toValidatedSignUpParams()
    is SignUpIndividualRawParams -> toValidatedSignUpParams()
}