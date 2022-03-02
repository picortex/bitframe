package businesses

import bitframe.client.UIScopeConfig
import expect.expect
import expect.toBe
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.businesses.forms.CreateBusinessFormFields
import pimonitor.client.businesses.forms.CreateBusinessViewModel
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.client.businesses.forms.CreateBusinessState as State

@Ignore
class CreateBusinessViewModelTest {
    val api = PiMonitorApiTest()
    val vm = CreateBusinessViewModel(UIScopeConfig(api, recoveryTime = 0L))

    @Test
    fun should_be_able_to_go_to_show_add_business_form_state() = runTest {
        expect(vm.ui.value.status).toBe<Feedback.None>()
    }

    @Test
    fun should_be_able_to_submit_add_business_form() = runTest {
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
            contactEmail = "mmajapa@gmail$time.com",
            sendInvite = false
        )

        // STEP 4: Ensure the view model went through the expected states
        val state = State(fields = CreateBusinessFormFields().copy(params))
        vm.expect(Intent.SubmitForm(params)).toGoThrough(
            state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
            state.copy(status = Feedback.Success("${params.businessName} has successfully been added")),
            State()
        )
    }

    @Test
    fun should_have_same_values_when_there_is_an_error() = runTest {
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

        // STEP 4: Ensure the view model went through the expected states
        val state = State(fields = CreateBusinessFormFields().copy(params))
        vm.expect(Intent.SubmitForm(params)).toGoThrough(
            state.copy(status = Feedback.Loading(message = "Adding ${params.businessName}, please wait . . .")),
            state.copy(status = Feedback.Failure(message = "Property contactEmail must not be empty/blank")),
            state.copy(status = Feedback.None)
        )
    }

    @Test
    fun should_be_in_a_fillable_state_after_success_of_previous_business() = runTest {
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
            contactEmail = "mmajapa@gmail$time.com",
            sendInvite = false
        )

        // STEP 4: Ensure the view model went through the expected states
        val state = State(fields = CreateBusinessFormFields().copy(params))
        vm.expect(Intent.SubmitForm(params)).toGoThrough(
            state.copy(status = Feedback.Loading(message = "Adding ${params.businessName}, please wait . . .")),
            state.copy(status = Feedback.Success("${params.businessName} has successfully been added")),
            State()
        )

        // STEP 5: Ensure that the viewmodel is a state where people can input data
        expect(vm.ui.value).toBe(State())
    }
}