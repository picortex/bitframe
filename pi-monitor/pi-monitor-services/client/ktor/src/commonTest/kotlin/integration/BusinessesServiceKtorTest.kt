package integration

import expect.expect
import kotlinx.coroutines.runTest
import later.await
import bitframe.client.PiMonitorServiceKtor
import kotlin.test.Test

class BusinessesServiceKtorTest {
    val service = PiMonitorServiceKtor(CONFIG_UNDER_TEST)

    @Test
    fun should_fetch_all_businesses() = runTest {
        val all = service.businesses.all().await()
        expect(all).toBeOfSize(0)
    }
}