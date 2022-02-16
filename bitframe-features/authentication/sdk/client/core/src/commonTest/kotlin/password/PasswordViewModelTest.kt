package password

import bitframe.client.MockServiceConfig
import bitframe.client.MockUIScopeConfig
import bitframe.client.password.ChangePasswordIntent
import bitframe.client.password.ChangePasswordScope
import bitframe.client.profile.ProfileService
import bitframe.core.profile.params.ChangePasswordParams
import expect.expect
import expect.toBe
import kotlinx.coroutines.test.runTest
import live.Watcher
import presenters.feedbacks.Feedback
import viewmodel.expect
import kotlin.test.Test
import kotlin.test.assertEquals
import bitframe.client.password.ChangePasswordState as State

class PasswordViewModelTest {
    val service = ProfileService(MockServiceConfig())
    val scope = ChangePasswordScope(MockUIScopeConfig(service))
    val vm = scope.viewModel

    @Test
    fun should_start_in_a_loading_state() {
        val state = vm.ui.value
        expect(state).toBe(State())
    }

    @Test
    fun lists_can_have_equality() {
        assertEquals(
            listOf(
                State().copy(status = Feedback.Failure(message = "failure")),
            ).toString(),
            listOf(
                State().copy(status = Feedback.Failure(message = "failure"))
            ).toString()
        )
    }

    @Test
    fun should_fail_to_change_password_if_no_user_is_currently_logged_in() = runTest {
        val params = ChangePasswordParams(
            previous = "prev",
            current = "new"
        )
        val state = vm.ui.value
        vm.expect(ChangePasswordIntent(params)).toGoThrough(
            state.copy(status = Feedback.Loading(message = "Changing your password, please wait . . .")),
            state.copy(status = Feedback.Failure(message = "You need to be signed in first in order to change your password")),
            state
        )
    }
}