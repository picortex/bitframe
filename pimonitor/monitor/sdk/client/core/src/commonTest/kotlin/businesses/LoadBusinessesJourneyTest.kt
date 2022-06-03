package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.businesses.BusinessesViewModel
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.cases.CentralState
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.failure
import presenters.cases.loading
import utils.PiMonitorTestScope
import viewmodel.ViewModel
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent

class LoadBusinessesJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api = scope.api
    private val vm = scope.businesses.viewModel as BusinessesViewModel

    @Test
    fun should_fail_to_load_businesses_when_there_is_no_signed_in_user() = runTest {
        api.session.signOut()
        val state = vm.ui.value
        vm.expect(Intent.LoadBusinesses).toGoThrough(
            state.loading(message = "Loading businesses, please wait. . ."),
            state.failure(message = "You must be signed in to query businesses"),
        )
    }

    @Test
    fun should_load_business_after_load_businesses_is_called() = runTest {
        val time = Clock.System.now()
        // Step 1: Register as an individual monitor
        val params1 = SignUpIndividualParams(
            name = "John $time Doe",
            email = "john@doe$time.com",
            password = "johndoe"
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("John $time Doe")

        // Step 2: Sign in as the registered monitor
        val params2 = SignInParams(
            identifier = "john@doe$time.com",
            password = "johndoe"
        )
        api.signIn.signIn(params2).await()

        // Step 3: Create a monitored business
        val params3 = CreateMonitoredBusinessParams(
            businessName = "Test Business",
            contactEmail = "contact$time@test.com",
            contactName = "Jane Doe",
            sendInvite = true
        )
        api.businesses.create(params3).await()

        // Step 4: Load Businesses in the viewmodel
        vm.expect(Intent.LoadBusinesses)
        val state = vm.ui.value
        expect(state.table.rows.first().data.name).toBe("Test Business")
    }
}