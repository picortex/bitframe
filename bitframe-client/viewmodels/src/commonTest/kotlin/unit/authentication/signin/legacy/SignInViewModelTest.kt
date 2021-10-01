package unit.authentication.signin.legacy

import bitframe.BitframeTestClientImpl
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInFormFields
import bitframe.authentication.signin.legacy.SignInIntent.ShowForm
import bitframe.authentication.signin.legacy.SignInIntent.Submit
import bitframe.authentication.signin.legacy.SignInViewModel
import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import viewmodel.expect
import kotlin.test.Test
import bitframe.authentication.signin.legacy.SignInState as State

class SignInViewModelTest {
    private val service = BitframeTestClientImpl(TestClientConfiguration.of("app-id"))
    private val vm = SignInViewModel(service.signIn)

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = runTest {
        vm.expect(ShowForm(null)).toBeIn(State.Form(SignInFormFields()))
    }

    @Test
    fun should_be_in_a_show_form_state_with_credentials_passed_in_the_intent() = runTest {
        val credentials = SignInCredentials("username", "password")
        vm.expect(ShowForm(credentials)).toBeIn(State.Form(SignInFormFields.with(credentials)))
    }

    @Test
    fun should_show_be_in_a_conundrum_state_when_a_user_has_more_then_one_account() = runTest {
        val credentials = SignInCredentials("user02@test.com", "pass2")
        vm.expect(Submit(credentials))
        expect(vm.ui.value).toBe<State.Conundrum>()
    }

    @Test
    fun should_show_be_in_a_success_state_when_a_user_has_only_one_account() = runTest {
        val credentials = SignInCredentials("user01@test.com", "pass1")
        vm.expect(Submit(credentials))
        expect(vm.ui.value).toBe<State.Success>()
    }

    @Test
    fun should_successfuly_log_in_with_proper_credentials() = runTest {
        val credentials = SignInCredentials("user01@test.com", "pass1")
        vm.expect(Submit(credentials)).toGoThrough(
            State.Loading("Signing you in, please wait . . ."),
            State.Success("Logged in successfully")
        )
    }
}