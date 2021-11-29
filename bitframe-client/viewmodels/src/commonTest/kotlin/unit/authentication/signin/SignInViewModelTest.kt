package unit.authentication.signin

import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.client.signin.SignInServiceMockConfig
import bitframe.authentication.signin.*
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import bitframe.presenters.feedbacks.FormFeedback.Loading
import bitframe.presenters.feedbacks.FormFeedback.Success
import cache.MockCache
import expect.expect
import expect.toBe
import kotlinx.coroutines.runTest
import viewmodel.expect
import kotlin.test.Test

class SignInViewModelTest {

    val config = SignInServiceMockConfig(
        users = mutableListOf(
            User(
                uid = "user-1",
                name = "User One",
                tag = "usr01",
                contacts = Contacts("user1@test.com"),
                spaces = listOf(
                    Space(
                        uid = "space-1",
                        name = "User One's Space",
                        type = "",
                        scope = ""
                    )
                )
            )
        )
    )
    private val vm = SignInViewModel(SignInServiceMock(config), MockCache())

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = runTest {
        expect(vm.ui.value).toBe(SignInState.Form(SignInFormFields(), null))
    }

    @Test
    fun should_be_in_a_conundrum_state_when_a_user_has_more_then_one_space() = runTest {
        val credentials = SignInCredentials("user1@test.com", "pass2")
        vm.expect(SignInIntent.Submit(credentials))
        val state = vm.ui.value as SignInState.Form
        expect(state.status).toBe<Success>()
    }

    @Test
    fun should_be_in_a_success_state_when_a_user_has_only_one_space() = runTest {
        val credentials = SignInCredentials("user1@test.com", "pass1")
        vm.expect(SignInIntent.Submit(credentials))
        val state = vm.ui.value as SignInState.Form
        expect(state.status).toBe<Success>()
    }

    @Test
    fun should_successfuly_log_in_with_proper_credentials() = runTest {
        val credentials = SignInCredentials("user1@test.com", "pass1")
        val state = SignInState.Form(SignInFormFields(), null)
        vm.expect(SignInIntent.Submit(credentials)).toGoThrough(
            state.copy(status = Loading("Signing you in, please wait . . .")),
            state.copy(status = Loading("Saving your session for next login")),
            state.copy(status = Success("Logged in successfully"))
        )
    }

    @Test
    fun should_go_back_to_a_valid_form_state_after_an_error_has_occured() = runTest {
        val credentials = SignInCredentials("user01", "pass1")
        val state = SignInState.Form(SignInFormFields().copy(credentials), null)
        val intent = SignInIntent.Submit(credentials)
        vm.expect(intent)
        expect(vm).toBeIn(state)
    }
}