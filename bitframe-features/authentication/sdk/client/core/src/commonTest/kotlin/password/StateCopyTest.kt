package password

import bitframe.client.password.ChangePasswordState
import bitframe.core.profile.params.ChangePasswordParams
import expect.expect
import kotlin.test.Test

class StateCopyTest {
    @Test
    fun change_password_copy_should_work() {
        val input = ChangePasswordState()
        val params = ChangePasswordParams(
            previous = "prev",
            current = "curr"
        )
        val output = input.copy(params = params)
        expect(output.fields.previous.value).toBe("prev")
        expect(output.fields.current.value).toBe("curr")
    }
}