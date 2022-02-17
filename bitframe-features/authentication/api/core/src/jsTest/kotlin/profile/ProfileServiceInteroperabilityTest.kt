package profile

import bitframe.client.profile.ProfileServiceMock
import expect.expect
import kotlinx.coroutines.test.TestResult
import utils.globalThis
import utils.jso
import kotlin.js.Promise
import kotlin.test.Test

class ProfileServiceInteroperabilityTest {
    init {
        globalThis.service = ProfileServiceMock()
    }

    val service = globalThis.service

    @Test
    fun should_be_able_to_change_password(): TestResult = service.changePassword(jso {
        previous = "previous_pass"
        current = "current_password"
    }).asPromise().catch { it: Throwable ->
        expect(it.message).toBe("You need to be signed in first in order to change your password")
    }.unsafeCast<Promise<Unit>>()
}
