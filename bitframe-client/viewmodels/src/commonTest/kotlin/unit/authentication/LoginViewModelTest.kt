package unit.authentication

import bitframe.authentication.LoginCredentials
import bitframe.authentication.signIn.SignInViewModel
import bitframe.authentication.signIn.SignInIntent.Submit
import bitframe.authentication.signIn.SignInIntent.ShowForm
import bitframe.authentication.signIn.SignInState
import bitframe.authentication.TestSignInService
import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import viewmodel.expect
import kotlin.test.Test

class LoginViewModelTest {
    private val service = TestSignInService()
    private val vm = SignInViewModel(service)

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = runTest {
        vm.expect(ShowForm(null)).toBeIn(SignInState.Form(null))
    }

    @Test
    fun should_be_in_a_show_form_state_with_credentials_passed_in_the_intent() = runTest {
        val credentials = LoginCredentials("username", "password")
        vm.expect(ShowForm(credentials)).toBeIn(SignInState.Form(credentials))
    }

    @Test
    fun should_show_be_in_a_conundrum_state_when_a_user_has_more_then_one_account() = runTest {
        val credentials = LoginCredentials("user2", "pass2")
        vm.expect(Submit(credentials))
        expect(vm.ui.value).toBe<SignInState.Conundrum>()
    }

    @Test
    fun should_show_be_in_a_success_state_when_a_user_has_only_one_account() = runTest {
        val credentials = LoginCredentials("user1", "pass1")
        vm.expect(Submit(credentials))
        expect(vm.ui.value).toBe<SignInState.Success>()
    }
}