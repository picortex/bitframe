package acceptance.utils

import bitframe.PiMonitorBrowserApplication
import bitframe.PiMonitorBrowserApplicationImpl
import pimonitor.testing.PiMonitorContainerTest

open class PiMonitorAcceptanceTest : PiMonitorContainerTest() {
    companion object {
        val application: PiMonitorBrowserApplication get() = PiMonitorBrowserApplicationImpl(urlUnderTest)
    }
}