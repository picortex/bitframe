package pimonitor.authentication.signup

import bitframe.authentication.LoginConundrum
import bitframe.server.http.HttpRequest
import duality.Result
import duality.catching

class SignUpController {
    fun signIn(req: HttpRequest): Result<LoginConundrum> = catching<LoginConundrum> {
        TODO()
    }
}