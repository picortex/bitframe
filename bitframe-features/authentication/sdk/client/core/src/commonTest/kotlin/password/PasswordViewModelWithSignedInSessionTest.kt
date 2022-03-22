package password

import bitframe.client.ServiceConfigMock
import bitframe.client.MockUIScopeConfig
import bitframe.client.password.ChangePasswordIntent
import bitframe.client.password.ChangePasswordScope
import bitframe.client.profile.ProfileService
import bitframe.client.profile.ProfileServiceMock
import bitframe.core.App
import bitframe.core.Session
import bitframe.core.Space
import bitframe.core.User
import bitframe.core.profile.params.ChangePasswordParams
import expect.expect
import kotlinx.collections.interoperable.listOf
import kotlinx.coroutines.test.runTest
import live.mutableLiveOf
import presenters.cases.Feedback
import viewmodel.expect
import kotlin.test.Test

class PasswordViewModelWithSignedInSessionTest {
    val service: ProfileService = ProfileServiceMock(
        ServiceConfigMock(
            session = mutableLiveOf(
                Session.SignedIn(
                    app = App("test-app"),
                    space = Space(name = "test-space", type = "TEST_SPACE"),
                    user = User(name = "Test User"),
                    spaces = listOf(
                        Space(name = "test-space", type = "TEST_SPACE")
                    )
                )
            )
        )
    )
    val scope = ChangePasswordScope(MockUIScopeConfig(service))
    val vm = scope.viewModel

    @Test
    fun should_fail_to_change_password_with_any_blank_field() = runTest {
        val params = ChangePasswordParams(
            previous = "",
            current = "new"
        )
        val state = vm.ui.value
        vm.expect(ChangePasswordIntent(params)).toGoThrough(
            state.copy(params = params, status = Feedback.Loading(message = "Changing your password, please wait . . .")),
            state.copy(params = params, status = Feedback.Failure(message = "Property previous must not be empty/blank")),
            state.copy(params = params)
        )
    }

    @Test
    fun should_be_able_to_handle_errors_from_the_service() = runTest {
        val params = ChangePasswordParams(
            previous = "old",
            current = "new"
        )
        val states = vm.expect(ChangePasswordIntent(params)).value.filter { it.status is Feedback.Failure }
        expect(states).toHave(length = 1)
    }
}