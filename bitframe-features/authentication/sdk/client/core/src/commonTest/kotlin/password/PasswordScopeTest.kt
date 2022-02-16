package password

import bitframe.client.MockServiceConfig
import bitframe.client.MockUIScopeConfig
import bitframe.client.password.ChangePasswordScope
import bitframe.client.profile.ProfileService
import expect.expect
import kotlin.test.Test
import bitframe.client.password.ChangePasswordState as State

class PasswordScopeTest {
    val service = ProfileService(MockServiceConfig())
    val scope = ChangePasswordScope(MockUIScopeConfig(service))
    val vm = scope.viewModel

    @Test
    fun should_start_in_a_loading_state() {
        val state = vm.ui.value
        expect(state).toBe(State())
    }

    @Test
    fun should_fail_to_change_password_if_no_user_is_currently_logged_in() {
        
    }
}