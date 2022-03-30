import bitframe.client.jso
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.signup.params.BusinessSignUpParams
import pimonitor.core.signup.params.IndividualSignUpRawParams
import kotlin.test.Test

class AuthFlowInteroperabilityTest {
    val service: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val time = Clock.System.now()
        val params = jso<dynamic> {
            name = "John Doe $time"
            email = "john@doe$time.com"
            password = "john@doe1.com"
        }.unsafeCast<IndividualSignUpRawParams>()
        val res = service.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe $time")
    }

    @Test
    fun should_fail_if_not_all_entries_where_entered() = runTest {
        val err = expectFailure {
            val params = jso<dynamic> {
                name = "John Doe 1"
                email = "john@doe1.com"
            }.unsafeCast<IndividualSignUpRawParams>()
            service.signUp.signUp(params).await()
        }
        expect(err.message).toBe("Field password is required but was not found")
    }
}