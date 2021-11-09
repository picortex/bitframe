@file:JsExport

package pimonitor.client.monitors

import kotlinx.coroutines.launch
import later.await
import live.Live
import pimonitor.monitors.Monitor
import pimonitor.monitors.MonitorsService
import kotlin.js.JsExport
import bitframe.authentication.signin.Session as SignInSession

abstract class MonitorsService(
    override val config: MonitorsServiceConfig
) : MonitorsService(config) {

    val signInSession get() = config.signInSession

    val session get() = config.monitorSession

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
}