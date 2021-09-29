package acceptance.utils

import pimonitor.Application
import pimonitor.WebApplication
import testing.ContainerTest

open class AcceptanceTest : ContainerTest() {
    companion object {
        val application: Application get() = WebApplication(urlUnderTest)
    }
}