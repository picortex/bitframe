package portfolio

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.portfolio.PortfolioViewModel
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.portfolio.PortfolioData
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import utils.PiMonitorMockScope
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioViewModelTest {

    val scope = PiMonitorMockScope()
    val api = scope.api
    val vm = scope.portfolio.viewModel

    @Test
    @Ignore // Publish contacts first then proceed with this shit
    fun should_load_portfolio_data() = runTest {
        val time = Clock.System.now()
        // STEP 1: SignUp as a monitor
        val monitor = IndividualSignUpParams(
            name = "Jane $time Doe",
            email = "jane@doe$time.com",
            password = "jane"
        )
        api.signUp.signUp(monitor).await()

        //STEP 2: Sign In as the registered monitor
        api.signIn.signIn(monitor.toSignInCredentials()).await()

        // STEP 3: Create a monitored business
        val params = CreateMonitoredBusinessParams(
            businessName = "PiCortex LLC",
            contactName = "Mohammed Majapa",
            contactEmail = "",
            sendInvite = false
        )

        // STEP 4: Ensure viewmodel has gone through the expected states
        val state = State()
        vm.expect(Intent.LoadPortfolioData).toGoThrough(
            state.copy(status = Feedback.Loading(message = "Loading your portfolio data, please wait . . .")),
            state.copy(status = Feedback.None, data = PortfolioData())
        )
    }
}