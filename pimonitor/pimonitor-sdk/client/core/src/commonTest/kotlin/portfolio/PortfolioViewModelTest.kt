package portfolio

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import pimonitor.core.signup.params.toSignInParams
import presenters.cases.Feedback
import utils.PiMonitorTestScope
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioViewModelTest {

    private val scope = PiMonitorTestScope()
    private val api = scope.api
    private val vm = scope.portfolio.viewModel

    @Test
    fun should_load_portfolio_data() = runTest {
        val time = Clock.System.now()
        // STEP 1: SignUp as a monitor
        val monitor = SignUpIndividualParams(
            name = "Jane $time Doe",
            email = "jane@doe$time.com",
            password = "jane"
        )
        api.signUp.signUp(monitor).await()

        //STEP 2: Sign In as the registered monitor
        api.signIn.signIn(monitor.toSignInParams()).await()

        // STEP 3: Ensure viewmodel has gone through the expected states and has an empty portfolio data
        val state3 = State()
        vm.expect(Intent.LoadPortfolioData).toGoThrough(
            state3.copy(status = Feedback.Loading(message = "Loading your portfolio data, please wait . . .")),
            state3.copy(status = Feedback.None, data = api.portfolio.load().await())
        )

        // STEP 4: Create a monitored business
        val params = CreateMonitoredBusinessParams(
            businessName = "PiCortex LLC",
            contactName = "Mohammed Majapa",
            contactEmail = "contact@exp$time.com",
            sendInvite = false
        )
        api.businesses.create(params).await()

        // STEP 5: Ensure viewmodel has gone through the expected states and has 1 business
        val state = State()
        vm.expect(Intent.LoadPortfolioData).toGoThrough(
            state.copy(status = Feedback.Loading(message = "Loading your portfolio data, please wait . . .")),
            state.copy(status = Feedback.None, data = api.portfolio.load().await())
        )
    }
}