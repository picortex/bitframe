import bitframe.client.MicroScope
import bitframe.client.expect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.runTest
import logging.console
import viewmodel.ViewModel
import kotlin.test.Test

class UIScopesTestability {

    class TestVM : ViewModel<Int, Int>(0) {
        override fun CoroutineScope.execute(i: Int): Any {
            return console.log(i)
        }
    }

    class TestIntents(vm: TestVM)

    fun TestScope() = MicroScope {
        viewModel(TestVM())
        intents(TestIntents(viewModel))
    }

    @Test
    fun scopes_should_be_testable() = runTest {
        val scope by TestScope()
        scope.expect(3).toGoThrough()
    }
}