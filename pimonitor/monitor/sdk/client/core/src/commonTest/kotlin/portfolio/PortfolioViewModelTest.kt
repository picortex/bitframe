package portfolio

import later.await
import pimonitor.client.portfolio.PortfolioViewModel
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import pimonitor.core.signup.params.toSignInParams
import presenters.cases.copy
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.portfolio.PortfolioIntent as Intent

class PortfolioViewModelTest {

    private val scope = PiMonitorTestScope()
    private val api = scope.api
    private val vm = scope.portfolio.viewModel as PortfolioViewModel

    @Test
    fun should_load_portfolio_data() = runSequence {
        val params = step("SignUp as a monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane $time Doe",
                email = "jane@doe$time.com",
                password = "jane"
            )
            api.signUp.signUp(monitor).await()
            monitor
        }

        step("Sign In as the registered monitor") {
            api.signIn.signIn(params.toSignInParams()).await()
        }

        step("Ensure viewmodel has gone through the expected states and has an empty portfolio data") {
            val state = vm.ui.value
            vm.expect(Intent.LoadPortfolioData).toContain(
                state.copy(message = "Loading your portfolio data, please wait . . ."),
            )
        }

        step("Create a monitored business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "PiCortex LLC",
                contactName = "Mohammed Majapa",
                contactEmail = "contact@exp$time.com",
                sendInvite = false
            )
            api.businesses.create(params).await()
        }

        step("Ensure viewmodel has gone through the expected states and has 1 business") {
            val state = vm.ui.value
            vm.expect(Intent.LoadPortfolioData).toContain(
                state.copy(message = "Loading your portfolio data, please wait . . ."),
            )
        }
    }
}