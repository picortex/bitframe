package acceptance.utils

import bitframe.BrowserApplication
import bitframe.WebApplication
import bitframe.testing.ContainerTest

open class AcceptanceTest : ContainerTest() {
    companion object {
        val application: BrowserApplication get() = WebApplication(urlUnderTest)
    }
}