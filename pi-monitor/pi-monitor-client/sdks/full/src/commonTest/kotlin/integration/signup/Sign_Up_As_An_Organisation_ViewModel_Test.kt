@file:Suppress("ClassName")

package integration.signup

import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import pimonitor.Monitor
import pimonitor.MonitorParams
import pimonitor.MonitorType
import pimonitor.authentication.signup.NameEmailFormParams
import pimonitor.presenters.TextInputField
import pimonitor.authentication.signup.SignUpViewModel
import pimonitor.presenters.ButtonInputField
import utils.SERVICE_UNDER_TEST
import viewmodel.expect
import kotlin.test.Test
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class Sign_Up_As_An_Organisation_ViewModel_Test {
    @Test
    fun the_register_should_start_at_the_selection_screen() {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        expect(vm.ui.value).toBe<State.SelectRegistrationType>()
    }

    @Test
    fun the_register_should_be_able_to_move_from_selection_screen_to_organisation_info_form_screen() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        val params = NameEmailFormParams(
            title = "Enter your organisation's information",
            name = TextInputField("Organisation's Name", "John Doe Enterprises"),
            email = TextInputField("Organisation's Email", "support@enterpries.com"),
            nextButton = ButtonInputField("Submit"),
            prevButton = ButtonInputField("Back") {}
        )

        val expectedState = State.NameEmailForm(params, null)
        vm.expect(Intent.RegisterAsOrganization(null)).toBeIn(expectedState)

        // Enter business info
        vm.expect(Intent.SubmitForm(MonitorParams("John Doe Enterprises", "doe@enterprises.com"))).toBeIn<State.NameEmailForm>()

        vm.expect(Intent.SubmitForm(MonitorParams("John Doe", "john@doe.com"))).toGoThrough(
            State.Loading("Submitting your registration, Please wait . . ."),
            State.Success("Registration completed successfully")
        )
    }
}