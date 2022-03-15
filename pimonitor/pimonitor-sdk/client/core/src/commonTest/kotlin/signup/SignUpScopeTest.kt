package signup

import kotlinx.coroutines.test.runTest
import utils.PiMonitorTestScope
import kotlin.test.Test

class SignUpScopeTest {

    val appScope = PiMonitorTestScope()

    @Test
    fun can_send_intents_easily() = runTest {
        val scope = appScope.signUp
//        scope.viewModel.ui.watch(WatchMode.EAGERLY) {}
        scope.changeRegistrationType(scope.TYPE_BUSINESS + "dsdf")
    }
}