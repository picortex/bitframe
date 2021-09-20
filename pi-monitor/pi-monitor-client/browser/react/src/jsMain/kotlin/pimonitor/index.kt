package pimonitor

import applikation.konfig
import bitframe.Bitframe
import bitframe.BitframeTestClientImpl
import bitframe.authentication.TestClientConfiguration
import kotlinext.js.jso
import kotlinx.browser.document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.extensions.By
import kotlinx.extensions.get
import logging.ConsoleAppender
import logging.Logging
import org.w3c.dom.HTMLDivElement
import pimonitor.authentication.signup.SignUp
import pimonitor.evaluation.business.BusinessContainer
import pimonitor.test.PiMonitorServiceTest
import platform.Platform
import react.RBuilder
import react.dom.*
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val configuration = TestClientConfiguration(appId = "test-client", simulationTime = 3000)
    val client: PiMonitorService = PiMonitorServiceTest(configuration)
    val version: String by konfig()
    Logging.init(ConsoleAppender())
    Bitframe(
        client = client,
        routeRenderers = mapOf(
            "/authentication/sign-up" to { SignUp(client) }
        ),
        moduleRenderers = mapOf(
            "/evaluation/business" to { BusinessContainer(client) }
        ),
        version = version
    )
}