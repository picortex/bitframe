package integration

import expect.expect
import kotlinx.coroutines.runTest
import later.await
import bitframe.testing.annotations.Lifecycle
import bitframe.testing.annotations.TestInstance
import bitframe.testing.annotations.Testcontainers
import pimonitor.testing.PiMonitorIntegrationTest
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class BusinessesServiceKtorTest : PiMonitorIntegrationTest() {
    @Test
    fun should_fetch_all_businesses() = runTest {
        val all = piMonitorService.businesses.all().await()
        expect(all).toBeOfSize(0)
    }
}