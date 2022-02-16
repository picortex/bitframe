package profile

import bitframe.core.profile.ProfileService
import utils.jso
import kotlin.test.Test

class ProfileServiceInteroperabilityTest {
    val service: ProfileService by lazy { TODO() }

    @Test
    fun should_be_able_to_change_password() {
        service.changePassword(jso<dynamic> {
            previous = "previous_pass"
            current = "current_password"
        }.unsafeCast<Any>())
    }
}