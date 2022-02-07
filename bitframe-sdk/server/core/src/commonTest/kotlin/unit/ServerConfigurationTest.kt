package unit

import bitframe.server.serverConfiguration
import kotlin.test.Test

class ServerConfigurationTest {
    @Test
    fun should_have_a_good_locking_syntax() {
        serverConfiguration {
            database {
                MockDaoFactory()
            }

            service {
                BitframeService(ServiceConfig.create(it))
            }
        }
    }
}