package pimonitor.core.signup

import bitframe.core.RequestBody
import later.Later

interface SignUpUseCase {
    fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>): Later<SignUpResult>
}