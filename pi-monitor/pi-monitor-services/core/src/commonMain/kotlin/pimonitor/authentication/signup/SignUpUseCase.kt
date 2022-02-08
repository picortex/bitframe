package pimonitor.authentication.signup

import bitframe.service.requests.RequestBody
import later.Later

interface SignUpUseCase {
    fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>): Later<SignUpResult>
}