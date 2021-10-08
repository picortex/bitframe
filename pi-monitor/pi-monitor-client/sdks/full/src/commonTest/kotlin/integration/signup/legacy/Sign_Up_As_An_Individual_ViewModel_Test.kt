@file:Suppress("ClassName")

package integration.signup.legacy

import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.legacy.IndividualFormFields
import pimonitor.authentication.signup.legacy.SignUpViewModel
import utils.SERVICE_UNDER_TEST
import viewmodel.expect
import kotlin.test.Test
import pimonitor.authentication.signup.legacy.SignUpIntent as Intent
import pimonitor.authentication.signup.legacy.SignUpState as State

class Sign_Up_As_An_Individual_ViewModel_Test {
    @Test
    fun the_register_should_start_at_the_selection_screen() {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        expect(vm.ui.value).toBe<State.SelectRegistrationType>()
    }

    @Test
    fun the_register_should_be_able_to_move_from_selection_screen_to_personal_info_form_screen() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        val expectedState = State.IndividualForm(IndividualFormFields(), null)
        vm.expect(Intent.RegisterAsIndividual(null)).toBeIn(expectedState)

        vm.expect(Intent.SubmitIndividualForm(IndividualRegistrationParams("John Doe", "john@doe.com", "1234"))).toGoThrough(
            State.Loading("Submitting your registration, Please wait . . ."),
            State.Success("Registration completed successfully")
        )
    }
}