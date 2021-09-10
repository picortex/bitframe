package unit

import expect.expect
import kotlinx.coroutines.runTest
import pimonitor.Monitor
import pimonitor.MonitorParams
import pimonitor.authentication.signup.SignUpIntent.*
import pimonitor.authentication.signup.SignUpState
import pimonitor.authentication.signup.SignUpState.Form
import pimonitor.authentication.signup.SignUpViewModel
import pimonitor.toBusiness
import utils.SERVICE_UNDER_TEST
import viewmodel.expect
import kotlin.test.Test

class SignUpViewModelTest {

    @Test
    fun should_start_at_the_form_state() {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        expect(vm.ui.value).toBe(Form.Stage01(null))
    }

    private val PERSON_NAME = "John Doe"
    private val PERSON_EMAIL = "johndoe@johndoeinc.com"
    private val BUSINESS_NAME = "John Doe Inc."
    private val BUSINESS_EMAIL = "support@johndoeinc.com"

    private val businessParams = MonitorParams(BUSINESS_NAME, BUSINESS_EMAIL)
    private val testBusiness = Monitor.Business(name = BUSINESS_NAME, BUSINESS_EMAIL, null)

    @Test
    fun should_move_to_the_second_stage() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        vm.expect(Stage02(businessParams)).toGoThrough(Form.Stage02(businessParams, null))
    }

    @Test
    fun should_be_able_to_go_back_to_stage_one_from_stage_two() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        vm.expect(Stage02(businessParams))
        vm.expect(Stage01).toGoThrough(Form.Stage01(businessParams))
    }

    @Test
    fun should_be_able_to_catch_errors_if_fields_are_not_well_validated() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        val params = MonitorParams(name = "Test Business", email = "quavo")
        val intent = Stage02(params)
        val expectVmState = vm.expect(intent)
        expectVmState.toBeIn(Form.Stage01(params))
        val error = expectVmState.value.first() as SignUpState.Failure
        expect(error.cause.message).toBe("Invalid email: quavo")
    }

    @Test
    fun should_actually_register_a_monitor() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        val intent1 = Stage02(MonitorParams(BUSINESS_NAME, BUSINESS_EMAIL))
        vm.expect(intent1).toBeIn<Form.Stage02>()
        val intent2 = Submit(MonitorParams(PERSON_NAME, PERSON_EMAIL))
        vm.expect(intent2).toBeIn(SignUpState.Success("Your registration completed successfully"))
    }
}