package unit

import contacts.Email
import expect.expect
import kotlinx.coroutines.runTest
import pimonitor.Monitor
import pimonitor.MonitorParams
import pimonitor.authentication.signup.SignUpIntent.Stage01
import pimonitor.authentication.signup.SignUpIntent.Stage02
import pimonitor.authentication.signup.SignUpState
import pimonitor.authentication.signup.SignUpState.Form
import pimonitor.authentication.signup.SignUpViewModel
import pimonitor.toBusiness
import viewmodel.expect
import kotlin.test.Test

class SignUpViewModelTest {

    @Test
    fun should_start_at_the_form_state() {
        val vm = SignUpViewModel()
        expect(vm.ui.value).toBe(Form.Stage01(null))
    }

    private val BUSINESS_NAME = "John Doe Inc."
    private val BUSINESS_EMAIL = "support@johndoein.com"

    private val businessParams = MonitorParams(BUSINESS_NAME, BUSINESS_EMAIL)
    private val testBusiness = Monitor.Business(name = BUSINESS_NAME, BUSINESS_EMAIL, null)

    @Test
    fun should_move_to_the_second_stage() = runTest {
        val vm = SignUpViewModel()
        vm.expect(Stage02(businessParams)).toGoThrough(Form.Stage02(testBusiness))
    }

    @Test
    fun email_should_be_evaluated_by_value_and_not_by_reference() {
        expect(Email("andy@lamax.com")).toBe(Email("andy@lamax.com"))
    }

    @Test
    fun should_be_able_to_back_to_stage_one_from_stage_two() = runTest {
        val vm = SignUpViewModel()
        vm.expect(Stage02(businessParams))
        vm.expect(Stage01).toGoThrough(Form.Stage01(businessParams.toBusiness()))
    }

    @Test
    fun should_be_able_to_catch_errors_if_fields_are_not_well_validated() = runTest {
        val vm = SignUpViewModel()
        val intent = Stage02(
            MonitorParams(
                name = "Test Business",
                email = "quavo"
            )
        )

        val expectVmState = vm.expect(intent)
        expectVmState.toBeIn(Form.Stage01(null))
        val error = expectVmState.value.first() as SignUpState.Failure
        expect(error.cause.message).toBe("Invalid email: quavo")
    }
}