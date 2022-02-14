package unit

import bitframe.client.jso
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.api.PiMonitorApi
import pimonitor.api.PiMonitorApiMock
import pimonitor.authentication.signup.IRawIndividualSignUpParams
import kotlin.test.Test

class AuthFlowInteroperabilityTest {
    val service: PiMonitorApi = PiMonitorApiMock()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val params = jso<IRawIndividualSignUpParams> {
            name = "John Doe 1"
            email = "john@doe1.com"
            password = "john@doe1.com"
        }
        val res = service.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe 1")
    }

    @Test
    fun should_fail_if_not_all_entries_where_entered() = runTest {
        val err = expectFailure {
            val params = jso<IRawIndividualSignUpParams> {
                name = "John Doe 1"
                email = "john@doe1.com"
            }
            service.signUp.signUp(params).await()
        }
        expect(err.message).toBe("Property password is required")
    }
}