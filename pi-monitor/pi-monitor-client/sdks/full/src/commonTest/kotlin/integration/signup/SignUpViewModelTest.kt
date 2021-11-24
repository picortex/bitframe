package integration.signup

import bitframe.authentication.signup.SignUpViewModel
import bitframe.client.PiMonitorService
import bitframe.presenters.feedbacks.FormFeedback.*
import cache.MockCache
import kotlinx.coroutines.runTest
import pimonitor.testing.PiMonitorIntegrationTest
import pimonitor.testing.annotations.Lifecycle
import pimonitor.testing.annotations.TestInstance
import pimonitor.testing.annotations.Testcontainers
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class SignUpViewModelTest : PiMonitorIntegrationTest() {

    override val service: PiMonitorService by lazy {
        val cache = MockCache()
//        PiMonitorServiceStub()
//        when (val cfg = config) {
//            is KtorClientConfiguration -> PiMonitorServiceKtor(cfg, cache)
//            else -> PiMonitorServiceStub(PiMonitorServiceStubConfig(cfg.appId), cache)
//        }
    }

    val viewModel get() = SignUpViewModel(service.signUp, service.signIn)
    val vm by lazy { viewModel }

    private val testIndividualParams = SignUpParams.Individual(
        name = "John Doe", email = "john@email.com", password = "john@email.com"
    )

    private val testBusinessParams = SignUpParams.Business(
        businessName = "John Doe Inc", individualName = "John Doe", individualEmail = "john@doe.com", "1234"
    )

    @Test
    fun should_change_to_business_form_when_intent_is_initiated() = runTest {
        vm.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value)).toGoThrough(
            State.BusinessForm(BusinessFormFields(), null)
        )
    }

    @Test
    @Ignore // TODO fix in memory dao query, write its tests for god's sake
    fun should_be_able_to_submit_an_individual_form() = runTest {
        vm.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val state = State.IndividualForm(IndividualFormFields(), null)
        val intent = Intent.Submit.IndividualForm(testIndividualParams)
        vm.expect(intent).toGoThrough(
            state.copy(intent, Loading("Creating your account, please wait . . .")),
            state.copy(intent, Loading("Success. Signing you in, please wait . . .")),
            state.copy(intent, Success("Successfully signed in"))
        )
    }

    @Test
    fun should_fail_to_submit_with_invalid_email() = runTest {
        vm.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val params = testIndividualParams.copy(email = "johnemail.com")
        val intent = Intent.Submit.IndividualForm(params)
        val status = vm.expect(intent).value.firstNotNullOfOrNull {
            val s = it as? State.IndividualForm
            s?.status as? Failure
        } ?: throw AssertionError("Expected viewmodel to have error but did not")
        expect.expect(status.message).toBe("Failed to create your account")
        expect.expect(status.cause.message).toBe("Invalid email: johnemail.com")
    }

    @Test
    fun should_fail_to_submit_with_empty_name() = runTest {
        vm.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val params = testIndividualParams.copy(name = "")
        val intent = Intent.Submit.IndividualForm(params)
        val status = vm.expect(intent).value.firstNotNullOfOrNull {
            val s = it as? State.IndividualForm
            s?.status as? Failure
        } ?: throw AssertionError("Expected viewmodel to have error but did not")
        expect.expect(status.message).toBe("Failed to create your account")
        expect.expect(status.cause.message).toBe("Name must not be empty")
    }

    @Test
    fun should_be_able_to_submit_a_business_form() = runTest {
        vm.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value))
        val state = State.BusinessForm(BusinessFormFields(), null)
        val intent = Intent.Submit.BusinessForm(testBusinessParams)
        vm.expect(intent).toGoThrough(
            state.copy(intent, Loading("Creating your account, please wait . . .")),
            state.copy(intent, Loading("Success. Signing you in, please wait . . .")),
            state.copy(intent, Success("Successfully signed in"))
        )
    }
}
