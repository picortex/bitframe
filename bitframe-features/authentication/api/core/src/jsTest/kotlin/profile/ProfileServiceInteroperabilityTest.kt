package profile

import bitframe.client.profile.ProfileService
import bitframe.core.profile.params.RawChangePasswordParams
import kotlinx.coroutines.test.runTest
import later.await
import utils.jso
import kotlin.test.Test

class ProfileServiceInteroperabilityTest {
    val service: ProfileService by lazy { TODO() }

    @Test
    fun should_be_able_to_change_password() = runTest {
        service.changePassword(jso<dynamic> {
            previous = "previous_pass"
            current = "current_password"
        }.unsafeCast<RawChangePasswordParams>()).await()
    }
}