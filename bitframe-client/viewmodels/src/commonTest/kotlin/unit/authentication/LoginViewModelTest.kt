package unit.authentication

import bitframe.authentication.LoginCredentials
import bitframe.authentication.signIn.SignInViewModel
import bitframe.authentication.signIn.SignInViewModel.SignInIntent.Submit
import bitframe.authentication.signIn.SignInViewModel.SignInIntent.ShowForm
import bitframe.authentication.signIn.SignInViewModel.SignInState
import bitframe.authentication.TestSignInService
import expect.expect
import expect.toBe
import test.asyncTest
import viewmodel.test
import kotlin.test.Test

class LoginViewModelTest {
    private val service = TestSignInService()
    private val vm = SignInViewModel(service)

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = asyncTest {
        vm.test(intent = ShowForm(null), toHave = SignInState.Form(null))
    }

    @Test
    fun should_be_in_a_show_form_state_with_credentials_passed_in_the_intent() = asyncTest {
        val credentials = LoginCredentials("username", "password")
        vm.test(intent = ShowForm(credentials), toHave = SignInState.Form(credentials))
    }

    @Test
    fun should_show_be_in_a_conundrum_state_when_a_user_has_more_then_one_account() = asyncTest {
        val credentials = LoginCredentials("user2", "pass2")
        vm.test(intent = Submit(credentials))
        expect(vm.ui.value).toBe<SignInState.Conundrum>()
    }

    @Test
    fun should_show_be_in_a_success_state_when_a_user_has_only_one_account() = asyncTest {
        val credentials = LoginCredentials("user1", "pass1")
        vm.test(intent = Submit(credentials))
        expect(vm.ui.value).toBe<SignInState.Success>()
    }
}