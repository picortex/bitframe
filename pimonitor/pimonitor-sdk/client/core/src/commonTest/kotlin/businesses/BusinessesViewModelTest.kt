package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import utils.PiMonitorMockScope
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessesViewModelTest {

    val scope = PiMonitorMockScope()
    val api = scope.api
    val vm = scope.businesses.viewModel

    @Test
    fun should_start_in_a_loading_state() {
        val state = expect(vm).toBeIn<State>()
        expect(state.status).toBe(Feedback.Loading("Loading your businesses, please wait . . ."))
    }

    @Test
    fun should_fail_to_load_businesses_when_there_is_no_signed_in_user() = runTest {
        val state = State()
        vm.expect(Intent.LoadBusinesses).toGoThrough(
            state.copy(status = Feedback.Loading(message = "Loading your businesses, please wait . . .")),
            state.copy(status = Feedback.Failure(message = "You must be signed in to query businesses")),
            state
        )
    }

    @Test
    fun should_load_business_after_load_businesses_is_called() = runTest {
        val time = Clock.System.now()
        // Step 1: Register as an individual monitor
        val params1 = IndividualSignUpParams(
            name = "John $time Doe",
            email = "john@doe$time.com",
            password = "johndoe"
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("John $time Doe")

        // Step 2: Sign in as the registered monitor
        val params2 = SignInCredentials(
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