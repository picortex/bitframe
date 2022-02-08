package unit.authentication.signin

import bitframe.actors.users.RegisterUserParams
import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.api.BitframeServiceMock
import bitframe.api.BitframeServiceMockConfig
import bitframe.authentication.service.daod.usecase.RegisterUserUseCaseImpl
import bitframe.authentication.signin.*
import bitframe.client.BitframeViewModelConfig
import expect.expect
import expect.toBe
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import later.await
import live.value
import presenters.feedbacks.FormFeedback.Loading
import presenters.feedbacks.FormFeedback.Success
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test

class SignInViewModelTest {
    val serviceConfig = BitframeServiceMockConfig()

    val register = RegisterUserUseCaseImpl(serviceConfig)
    val service = BitframeServiceMock(serviceConfig)
    val viewModelConfig = BitframeViewModelConfig(service)
    private val vm = SignInViewModel(viewModelConfig)

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = runTest {
        expect(vm.ui.value).toBe(SignInState.Form(SignInFormFields(), null))
    }

    @Test
    @Ignore // TODO Figure out how to add a user space on bitframe level
    fun should_be_in_a_conundrum_state_when_a_user_has_more_then_one_space() = runTest {
        val credentials = SignInCredentials("user1@test.com", "pass2")
        vm.expect(SignInIntent.Submit(credentials))
        val state = vm.ui.value as SignInState.Form
        expect(state.status).toBe<Success>()
    }

    @Test
    fun should_be_in_a_success_state_when_a_user_has_only_one_space() = runTest {
        val params = RegisterUserParams(
            userName = "User One",
            userIdentifier = "user2@test.com",
            userPassword = "user2@test.com",
            spaceName = "User One",
            spaceType = "User One",
            spaceScope = "User One"
        )
        register.register(params).await()
        val credentials = SignInCredentials("user2@test.com", "user2@test.com")
        vm.expect(SignInIntent.Submit(credentials))
        val state = vm.ui.value as SignInState.Form
        expect(state.status).toBe<Success>()
    }

    @Test
    fun should_successfuly_log_in_with_proper_credentials() = runTest {
        val params = RegisterUserParams(
            userName = "User One",
            userIdentifier = "user1@test.com",
            userPassword = "pass1",
            spaceName = "User One",
            spaceType = "User One",
            spaceScope = "User One"
        )
        register.register(params).await()
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