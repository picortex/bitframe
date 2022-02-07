@file:JsExport

package pimonitor.client.monitors

import kotlinx.coroutines.launch
import later.await
import live.value
import pimonitor.monitors.Monitor
import pimonitor.monitors.MonitorsService
import kotlin.js.JsExport
import bitframe.service.Session as SignInSession

abstract class MonitorsService(
    override val config: MonitorsServiceConfig
) : MonitorsService(config) {

    val signInSession get() = config.session

    val session get() = config.monitorSession

    val currentMonitor: Monitor get() = session.currentMonitorOrThrow

    val currentMonitorOrNull: Monitor? get() = session.currentMonitorOrNull

    protected val scope get() = config.scope

    protected fun watchSignInSession() = signInSession.watch {
        scope.launch {
            session.value = if (it is SignInSession.SignedIn) {
                Session.Active(monitor(it.user.ref()).await())
            } else Session.Unknown
        }
    }
}