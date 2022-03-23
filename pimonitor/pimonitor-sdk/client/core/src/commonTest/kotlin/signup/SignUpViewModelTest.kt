package signup

import expect.expect
import expect.toBe
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import pimonitor.client.signup.fields.BusinessFormFields
import pimonitor.client.signup.fields.IndividualFormFields
import pimonitor.client.signup.fields.copy
import pimonitor.core.signup.params.BusinessSignUpParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.cases.Feedback
import utils.PiMonitorTestScope
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.signup.SignUpIntent as Intent
import pimonitor.client.signup.SignUpState as State

class SignUpViewModelTest {
    val scope = PiMonitorTestScope()
    val viewModel = scope.signUp.viewModel

    @Test
    @Ignore
    fun should_register_successfully() = runTest {
        val time = Clock.System.now()
        val params = IndividualSignUpParams(
            name = "John $time Doe",
            email = "john@doe$time.com",
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

    @Test
    fun should_change_to_business_form_when_intent_is_initiated() = runTest {
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value)).toGoThrough(
            State.BusinessForm(BusinessFormFields(), null)
        )
    }

    @Test
    fun should_be_able_to_sign_up_with_with_an_individual_form() = runTest {
        val time = Clock.System.now()
        val params = IndividualSignUpParams(
            name = "John $time Doe",
            email = "john@email$time.com",
            password = "john@email.com"
        )
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
        val intent = Intent.Submit.IndividualForm(params)
        viewModel.expect(intent).toBeIn<State.IndividualForm>()
        val state = viewModel.ui.value as State.IndividualForm
        expect(state.status).toBe<Feedback.Success>()
    }

    @Test
    fun should_fail_to_submit_with_invalid_email() = runTest {
        val params = IndividualSignUpParams(
            name = "John Doe",
            email = "johnemail.com",
            password = "john@email.com"
        )
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
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
        val params = IndividualSignUpParams(
            name = "",
            email = "john@email.com",
            password = "john@email.com"
        )
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_INDIVIDUAL.value))
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
        val time = Clock.System.now()
        val params = BusinessSignUpParams(
            businessName = "John Doe $time Inc",
            individualName = "John $time Doe",
            individualEmail = "john@doe$time.com",
            password = "1234"
        )
        viewModel.expect(Intent.ChangeRegistrationType(State.REGISTER_AS_BUSINESS.value))
        val intent = Intent.Submit.BusinessForm(params)
        viewModel.expect(intent)
        val state = viewModel.ui.value as State.BusinessForm
        expect(state.status).toBe<Feedback.Success>()
    }
}