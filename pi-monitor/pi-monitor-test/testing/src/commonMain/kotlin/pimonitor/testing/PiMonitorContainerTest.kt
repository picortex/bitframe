package pimonitor.testing

import bitframe.testing.ContainerTest
import bitframe.testing.TestMode

expect open class PiMonitorContainerTest() : ContainerTest {
    companion object {
        val mode: TestMode
        val urlUnderTest: String
    }
}