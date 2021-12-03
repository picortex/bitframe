package acceptance.utils

import pimonitor.PiMonitorBrowserApplication
import pimonitor.PiMonitorBrowserApplicationImpl
import pimonitor.testing.PiMonitorContainerTest

open class PiMonitorAcceptanceTest : PiMonitorContainerTest() {
    companion object {
        val application: PiMonitorBrowserApplication get() = PiMonitorBrowserApplicationImpl(urlUnderTest)
    }
}