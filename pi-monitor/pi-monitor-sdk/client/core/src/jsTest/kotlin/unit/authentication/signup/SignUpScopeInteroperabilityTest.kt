package unit.authentication.signup

import bitframe.api.jso
import kotlinx.coroutines.test.runTest
import live.WatchMode
import unit.utils.PiMonitorMockScope
import kotlin.test.Test

class SignUpScopeInteroperabilityTest {

    val appScope = PiMonitorMockScope()

    @Test
    fun can_send_intents_easily() = runTest {
        val scope = appScope.signUp
        scope.viewModel.ui.watch(WatchMode.EAGERLY) {}
        scope.submitBusinessForm(jso { })
    }
}