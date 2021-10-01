package unit.authentication.signin

import bitframe.BitframeTestClientImpl
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.*
import bitframe.presenters.feedbacks.FormFeedback.Loading
import bitframe.presenters.feedbacks.FormFeedback.Success
import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import viewmodel.expect
import kotlin.test.Test

class SignInViewModelTest {
    private val service = BitframeTestClientImpl(TestClientConfiguration.of("app-id"))
    private val vm = SignInViewModel(service.signIn)

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = runTest {
        expect(vm.ui.value).toBe(SignInState(SignInFormFields(), null))
    }

    @Test
    fun should_show_be_in_a_conundrum_state_when_a_user_has_more_then_one_account() = runTest {
        val credentials = SignInCredentials("user02@test.com", "pass2")
        vm.expect(SignInIntent.Submit(credentials))
        expect(vm.ui.value.status).toBe<Success>()
    }

    @Test
    fun should_show_be_in_a_success_state_when_a_user_has_only_one_account() = runTest {
        val credentials = SignInCredentials("user01@test.com", "pass1")
        vm.expect(SignInIntent.Submit(credentials))
        expect(vm.ui.value.status).toBe<Success>()
    }

    @Test
    fun should_successfuly_log_in_with_proper_credentials() = runTest {
        val credentials = SignInCredentials("user01@test.com", "pass1")
        val state = SignInState(SignInFormFields(), null)
        vm.expect(SignInIntent.Submit(credentials)).toGoThrough(
            state.copy(status = Loading("Signing you in, please wait . . .")),
            state.copy(status = Success("Logged in successfully"))
        )
    }

    @Test
    fun should_go_back_to_a_valid_form_state_after_an_error_has_occured() = runTest {
        val credentials = SignInCredentials("user1", "pass1")
        val state = SignInState(SignInFormFields().copy(credentials), null)
        val intent = SignInIntent.Submit(credentials)
        vm.expect(intent)
        expect(vm).toBeIn(state)
    }
}