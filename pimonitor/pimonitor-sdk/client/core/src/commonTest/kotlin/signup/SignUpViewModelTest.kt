package signup

import expect.expect
import expect.toBe
import kotlinx.coroutines.test.runTest
import pimonitor.client.signup.fields.BusinessFormFields
import pimonitor.client.signup.fields.IndividualFormFields
import pimonitor.client.signup.fields.copy
import pimonitor.core.signup.params.BusinessSignUpParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import utils.PiMonitorMockScope
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.signup.SignUpIntent as Intent
import pimonitor.client.signup.SignUpState as State

class SignUpViewModelTest {
    val scope = PiMonitorMockScope()
    val viewModel = scope.signUp.viewModel

    @Test
    @Ignore // TODO Fix this little bug here
    fun should_register_successfully() = runTest {
        val params = IndividualSignUpParams(
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
            status = Feedback.Success("Successfully signed in")
        )
        expect(viewModel).toBeIn(finalState)
    }

    private val testIndividualParams = IndividualSignUpParams(
        name = "John Doe", email = "john@email.com", password = "john@email.com"
    )

    private val testBusinessParams = BusinessSignUpParams(
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
        val intent = Intent.Submit.IndividualForm(testIndividualParams)
        viewModel.expect(intent).toBeIn<State.IndividualForm>()
        val state = viewModel.ui.value as State.IndividualForm
        expect(state.status).toBe<Feedback.Success>()
    }

    @Test
    fun should_fail_to_submit_with_invalid_email() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val params = testIndividualParams.copy(email = "johnemail.com")
        val intent = Intent.Submit.IndividualForm(params)
        val status = viewModel.expect(intent).value.firstNotNullOfOrNull {
            val s = it as? State.IndividualForm
            s?.status as? Feedback.Failure
        } ?: throw AssertionError("Expected viewmodel to have error but did not")
        expect(status.message).toBe("Failed to create your account: Identifier with value johnemail.com is neither a valid email or a valid phone number")
        expect(status.cause?.message).toBe("Identifier with value johnemail.com is neither a valid email or a valid phone number")
    }

    @Test
    fun should_fail_to_submit_with_empty_name() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val params = testIndividualParams.copy(name = "")
        val intent = Intent.Submit.IndividualForm(params)
        val status = viewModel.expect(intent).value.firstNotNullOfOrNull {
            val s = it as? State.IndividualForm
            s?.status as? Feedback.Failure
        } ?: throw AssertionError("Expected viewmodel to have error but did not")
        expect(status.message).toBe("Failed to create your account: Property name must not be empty/blank")
        expect(status.cause?.message).toBe("Property name must not be empty/blank")
    }

    @Test
    fun should_be_able_to_submit_a_business_form() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value))
        val intent = Intent.Submit.BusinessForm(testBusinessParams)
        viewModel.expect(intent)
        val state = viewModel.ui.value as State.BusinessForm
        expect(state.status).toBe<Feedback.Success>()
    }
}