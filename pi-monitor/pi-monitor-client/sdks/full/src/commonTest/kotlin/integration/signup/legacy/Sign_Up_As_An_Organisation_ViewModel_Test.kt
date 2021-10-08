@file:Suppress("ClassName")

package integration.signup.legacy

import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.MonitorBusinessParams
import pimonitor.authentication.signup.legacy.OrganisationFormFields
import pimonitor.authentication.signup.legacy.SignUpViewModel
import utils.SERVICE_UNDER_TEST
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.authentication.signup.legacy.SignUpIntent as Intent
import pimonitor.authentication.signup.legacy.SignUpState as State

class Sign_Up_As_An_Organisation_ViewModel_Test {
    @Test
    fun the_register_should_start_at_the_selection_screen() {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)
        expect(vm.ui.value).toBe<State.SelectRegistrationType>()
    }

    @Test
    @Ignore
    fun the_register_should_be_able_to_move_from_selection_screen_to_organisation_info_form_screen() = runTest {
        val vm = SignUpViewModel(SERVICE_UNDER_TEST.signUp)

        val expectedState = State.OrganisationForm(OrganisationFormFields())
        vm.expect(Intent.RegisterAsOrganization(null)).toBeIn(expectedState)

        // Enter business info
        val businessInfo = MonitorBusinessParams(
            name = "John Doe Enterprises",
            email = "doe@enterprises.com"
        )
        vm.expect(Intent.SubmitBusinessForm(businessInfo)).toBeIn<State.IndividualForm>()

        // Enter personal info
        val personalInfo = IndividualRegistrationParams(
            name = "John Doe",
            email = "john@doe.com",
            password = "1234"
        )

        vm.expect(Intent.SubmitIndividualForm(personalInfo)).toGoThrough(
            State.Loading("Submitting your registration, Please wait . . ."),
            State.Success("Registration completed successfully")
        )
    }
}