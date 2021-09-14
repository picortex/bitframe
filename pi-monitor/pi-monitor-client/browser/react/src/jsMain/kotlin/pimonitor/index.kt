package pimonitor

import applikation.konfig
import bitframe.Bitframe
import bitframe.authentication.TestClientConfiguration
import kotlinx.browser.document
import kotlinx.extensions.By
import kotlinx.extensions.get
import logging.ConsoleAppender
import logging.Logging
import org.w3c.dom.HTMLDivElement
import pimonitor.authentication.signup.SignUp
import pimonitor.test.PiMonitorServiceTest
import platform.Platform
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val configuration = TestClientConfiguration(appId = "test-client", simulationTime = 2000)
    val client: PiMonitorService = PiMonitorServiceTest(configuration)
    val version: String by konfig()
    Logging.init(ConsoleAppender())
    Bitframe(
        client = client,
        registration = { SignUp(client) },
        version = version
    )
    console.log(Platform)
}