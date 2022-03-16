package signup

import bitframe.client.jso
import kotlinx.coroutines.test.runTest
import live.WatchMode
import utils.PiMonitorTestScope
import kotlin.test.Test

class SignUpScopeInteroperabilityTest {

    val appScope = PiMonitorTestScope()

    @Test
    fun can_send_intents_easily() = runTest {
        val scope = appScope.signUp
        scope.viewModel.ui.watch(WatchMode.EAGERLY) {}
        scope.submitBusinessForm(jso { })
    }
}