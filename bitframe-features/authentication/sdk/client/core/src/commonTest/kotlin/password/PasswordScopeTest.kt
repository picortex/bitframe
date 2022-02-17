package password

import bitframe.client.MockServiceConfig
import bitframe.client.MockUIScopeConfig
import bitframe.client.password.ChangePasswordScope
import bitframe.client.profile.ProfileService
import bitframe.client.profile.ProfileServiceMock
import bitframe.core.profile.params.ChangePasswordParams
import kotlin.test.Test

class PasswordScopeTest {
    val service: ProfileService = ProfileServiceMock()
    val scope = ChangePasswordScope(MockUIScopeConfig(service))

    @Test
    fun should_fail_to_change_password_if_no_user_is_currently_logged_in() {
        scope.changePassword(
            ChangePasswordParams(
                previous = "prev",
                current = "new"
            )
        )
    }
}