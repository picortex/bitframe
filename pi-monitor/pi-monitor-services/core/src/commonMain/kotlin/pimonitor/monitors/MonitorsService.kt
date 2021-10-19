@file:JsExport

package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.service.config.ServiceConfig
import kotlinx.coroutines.launch
import later.Later
import later.await
import live.Live
import kotlin.js.JsExport
import bitframe.authentication.signin.Session as SignInSession

abstract class MonitorsService(
    open val signInSession: Live<SignInSession>,
    open val config: ServiceConfig
) {
    val session: Live<Session> = Live(Session.Unknown)
    protected val scope get() = config.scope

    protected fun watchSignInSession() = signInSession.watch {
        scope.launch {
            session.value = if (it is SignInSession.SignedIn) {
                Session.Active(monitor(it.user.ref()).await())
            } else Session.Unknown
        }
    }

    abstract fun load(uid: String): Later<Monitor?>
    abstract fun monitor(with: UserRef): Later<Monitor>
}