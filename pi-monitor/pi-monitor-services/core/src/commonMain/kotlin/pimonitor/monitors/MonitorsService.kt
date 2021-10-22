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

    val currentMonitor: Monitor
        get() = when (val s = session.value) {
            Session.Unknown -> error("Can't get a monitor, have you signed in?")
            is Session.Active -> s.monitor
        }

    val currentMonitorOrNull: Monitor?
        get() = try {
            currentMonitor
        } catch (c: Throwable) {
            null
        }

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