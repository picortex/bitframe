package password

import bitframe.client.MockUIScopeConfig
import bitframe.client.password.ChangePasswordIntent
import bitframe.client.password.ChangePasswordScope
import bitframe.client.profile.ProfileServiceMock
import bitframe.core.profile.params.ChangePasswordParams
import expect.expect
import kotlinx.coroutines.test.runTest
import presenters.cases.Feedback
import viewmodel.expect
import kotlin.test.Test
import bitframe.client.password.ChangePasswordState as State

class PasswordViewModelWithoutSignedInSessionTest {
    val service = ProfileServiceMock()
    val scope = ChangePasswordScope(MockUIScopeConfig(service))
    val vm = scope.viewModel

    @Test
    fun should_start_in_a_loading_state() {
        val state = vm.ui.value
        expect(state).toBe(State())
    }

    @Test
    fun should_fail_to_change_password_if_no_user_is_currently_logged_in() = runTest {
        val params = ChangePasswordParams(
            previous = "prev",
            current = "new"
        )
        val state = vm.ui.value
        vm.expect(ChangePasswordIntent(params)).toGoThrough(
            state.copy(params = params, status = Feedback.Loading(message = "Changing your password, please wait . . .")),
            state.copy(params = params, status = Feedback.Failure(message = "You need to be signed in first in order to change your password")),
            state.copy(params = params)
        )
    }

    @Test
    fun should_fail_to_change_password_with_any_blank_field() = runTest {
        val params = ChangePasswordParams(
            previous = "",
            current = "new"
        )
        val state = vm.ui.value
        vm.expect(ChangePasswordIntent(params)).toGoThrough(
            state.copy(params = params, status = Feedback.Loading(message = "Changing your password, please wait . . .")),
            state.copy(params = params, status = Feedback.Failure(message = "You need to be signed in first in order to change your password")),
            state.copy(params = params)
        )
    }
}