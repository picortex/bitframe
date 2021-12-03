package pimonitor.testing

import bitframe.testing.ContainerTest
import bitframe.testing.TestMode

actual open class PiMonitorContainerTest : ContainerTest() {
    actual companion object {
        actual val mode: TestMode get() = ContainerTest.mode
        actual val urlUnderTest: String get() = ContainerTest.urlUnderTest
    }
}