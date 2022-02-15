package signup

import kotlinx.coroutines.test.runTest
import pimonitor.client.signup.SignUpViewModel
import pimonitor.client.signup.copy
import pimonitor.client.signup.fields.BusinessFormFields
import pimonitor.client.signup.fields.IndividualFormFields
import pimonitor.client.signup.fields.copy
import pimonitor.core.signup.RawBusinessSignUpParams
import pimonitor.core.signup.RawIndividualSignUpParams
import pimonitor.core.signup.SignUpParams
import presenters.feedbacks.FormFeedback
import utils.PiMonitorMockScope
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.signup.SignUpIntent as Intent
import pimonitor.client.signup.SignUpState as State

class SignUpViewModelTest {
    val scope = PiMonitorMockScope()
    val viewModel = scope.signUp.viewModel as SignUpViewModel

    @Test
    @Ignore // TODO Fix this little bug here
    fun should_register_successfully() = runTest {
        val params = RawIndividualSignUpParams(
            name = "John Doe",
            email = "john@doe.com",
            password = "john@doe.com"
        )
        val initialFields = IndividualFormFields()
        val initialState = State.IndividualForm(initialFields, null)
        expect(viewModel).toBeIn(initialState)
        val intent = Intent.Submit.IndividualForm(params)
        viewModel.expect(intent)
        val finalState = initialState.copy(
            fields = initialFields.copy(intent),
            status = FormFeedback.Success("Successfully signed in")
        )
        expect(viewModel).toBeIn(finalState)
    }

    private val testIndividualParams = RawIndividualSignUpParams(
        name = "John Doe", email = "john@email.com", password = "john@email.com"
    )

    private val testBusinessParams = RawBusinessSignUpParams(
        businessName = "John Doe Inc", individualName = "John Doe", individualEmail = "john@doe.com", "1234"
    )

    @Test
    fun should_change_to_business_form_when_intent_is_initiated() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value)).toGoThrough(
            State.BusinessForm(BusinessFormFields(), null)
        )
    }

    @Test
    fun should_be_able_to_submit_an_individual_form() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val state = State.IndividualForm(IndividualFormFields(), null)
        val intent = Intent.Submit.IndividualForm(testIndividualParams)
        viewModel.expect(intent).toGoThrough(
            state.copy(intent, FormFeedback.Loading("Creating your account, please wait . . .")),
            state.copy(intent, FormFeedback.Loading("Success. Signing you in, please wait . . .")),
            state.copy(intent, FormFeedback.Success("Successfully signed in"))
        )
    }

    @Test
    fun should_fail_to_submit_with_invalid_email() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val params = testIndividualParams.copy(email = "johnemail.com")
        val intent = Intent.Submit.IndividualForm(params)
        val status = viewModel.expect(intent).value.firstNotNullOfOrNull {
            val s = it as? State.IndividualForm
            s?.status as? FormFeedback.Failure
        } ?: throw AssertionError("Expected viewmodel to have error but did not")
        expect.expect(status.message).toBe("Failed to create your account: Invalid email: johnemail.com")
        expect.expect(status.cause.message).toBe("Invalid email: johnemail.com")
    }

    @Test
    fun should_fail_to_submit_with_empty_name() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val params = testIndividualParams.copy(name = "")
        val intent = Intent.Submit.IndividualForm(params)
        val status = viewModel.expect(intent).value.firstNotNullOfOrNull {
            val s = it as? State.IndividualForm
            s?.status as? FormFeedback.Failure
        } ?: throw AssertionError("Expected viewmodel to have error but did not")
        expect.expect(status.message).toBe("Failed to create your account: Name must not be empty")
        expect.expect(status.cause.message).toBe("Name must not be empty")
    }

    @Test
    fun should_be_able_to_submit_a_business_form() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value))
        val state = State.BusinessForm(BusinessFormFields(), null)
        val intent = Intent.Submit.BusinessForm(testBusinessParams)
        viewModel.expect(intent).toGoThrough(
            state.copy(intent, FormFeedback.Loading("Creating your account, please wait . . .")),
            state.copy(intent, FormFeedback.Loading("Success. Signing you in, please wait . . .")),
            state.copy(intent, FormFeedback.Success("Successfully signed in"))
        )
    }
}