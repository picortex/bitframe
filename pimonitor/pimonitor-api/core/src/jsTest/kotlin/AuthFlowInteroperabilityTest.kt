import bitframe.client.jso
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import pimonitor.core.signup.IRawIndividualSignUpParams
import kotlin.test.Test

class AuthFlowInteroperabilityTest {
    val service: PiMonitorApi = PiMonitorApiMock()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val params = jso<dynamic> {
            name = "John Doe 1"
            email = "john@doe1.com"
            password = "john@doe1.com"
        }.unsafeCast<IRawIndividualSignUpParams>()
        val res = service.signUp.signUpAsBusiness(params).await()
        expect(res.user.name).toBe("John Doe 1")
    }

    @Test
    fun should_fail_if_not_all_entries_where_entered() = runTest {
        val err = expectFailure {
            val params = jso<dynamic> {
                name = "John Doe 1"
                email = "john@doe1.com"
            }.unsafeCast<IRawIndividualSignUpParams>()
            service.signUp.signUpAsBusiness(params).await()
        }
        expect(err.message).toBe("Property password is required")
    }
}