package profile

import bitframe.client.MockUIScopeConfig
import bitframe.client.profile.ProfileScope
import bitframe.client.profile.ProfileServiceMock
import bitframe.client.profile.ProfileState
import expect.expect
import kotlin.test.Test

class ProfileScopeTest {
    val service = ProfileServiceMock()
    val scope = ProfileScope(MockUIScopeConfig(service))
    val vm = scope.viewModel

    @Test
    fun should_not_start_if_no_user_is_currently_logged_in() {
        val state = vm.ui.value
        expect(state.status?.message).toBe("Initializing your profile, please wait . . .")
    }
}