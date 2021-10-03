package acceptance.utils

import pimonitor.Application
import pimonitor.WebApplication
import testing.containers.ContainerTest.Companion.urlUnderTest

open class AcceptanceTest : ContainerTest {
    companion object {
        val application: Application get() = WebApplication(urlUnderTest)
    }
}