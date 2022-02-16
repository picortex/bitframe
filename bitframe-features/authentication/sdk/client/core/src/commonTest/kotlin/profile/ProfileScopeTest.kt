package profile

import bitframe.client.MockServiceConfig
import bitframe.client.MockUIScopeConfig
import bitframe.client.profile.ProfileScope
import bitframe.client.profile.ProfileService
import bitframe.client.profile.ProfileState
import expect.expect
import kotlin.test.Test

class ProfileScopeTest {
    val service = ProfileService(MockServiceConfig())
    val scope = ProfileScope(MockUIScopeConfig(service))
    val vm = scope.viewModel

    @Test
    fun should_not_start_if_no_user_is_currently_logged_in() {
        val state = vm.ui.value as ProfileState
        expect(state.status?.message).toBe("Initializing your profile, please wait . . .")
    }

    @Test
    fun should_fail_to_initialize_the_scope_if_there_is_no_user_logged_in() {
        scope.initialize()
        val state = vm.ui.value
        expect(state.status?.message).toBe("Can not initialize a profile of a user who is not signed in")
    }
}