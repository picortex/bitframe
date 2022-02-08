package bitframe.authentication.client.signin

import bitframe.actors.apps.App
import bitframe.authentication.signin.SignInResult
import bitframe.authentication.signin.SignInCredentials
import bitframe.service.Session
import bitframe.service.requests.RequestBody
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.later
import live.value

open class SignInServiceMock(
    override val config: SignInServiceMockConfig = SignInServiceMockConfig()
) : SignInService(config) {
    override fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult> = scope.later {
        val matches = config.users.filter {
//            it.contacts.contains(credentials.identifier)
            false
        }
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${rb.data.identifier}, not found")
        val match = matches.first()
        SignInResult(
            user = match,
            spaces = match.spaces.toInteroperableList()
        ).also {
            session.value = if (it.spaces.size > 1) {
                Session.Conundrum(App(config.appId), it.spaces, it.user)
            } else {
                Session.SignedIn(App(config.appId), it.spaces.first(), it.user, it.spaces)
            }
        }
    }
}