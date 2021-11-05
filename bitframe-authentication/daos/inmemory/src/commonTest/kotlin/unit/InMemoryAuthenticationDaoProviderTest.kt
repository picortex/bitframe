package unit

import bitframe.authentication.InMemoryAuthenticationDaoProvider
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import kotlin.test.Test

class InMemoryAuthenticationDaoProviderTest {
    val provider = InMemoryAuthenticationDaoProvider()

    @Test
    fun should_start_with_test_data_loaded_into_the_respective_daos() = runTest {
        val users = provider.users.all().await()
        expect(users).toBeOfSize(0)
    }
}