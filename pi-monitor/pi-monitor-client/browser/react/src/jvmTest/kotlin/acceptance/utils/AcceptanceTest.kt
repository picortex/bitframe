package acceptance.utils

import bitframe.Application
import bitframe.WebApplication
import pimonitor.testing.PiMonitorContainerTest

open class AcceptanceTest : PiMonitorContainerTest() {
    companion object {
        val application: Application get() = WebApplication(urlUnderTest)
    }
}