package pimonitor

import applikation.konfig
import bitframe.Bitframe
import bitframe.service.config.ClientConfiguration
import client
import kotlinext.js.jso
import kotlinx.browser.document
import kotlinx.extensions.By
import kotlinx.extensions.get
import logging.ConsoleAppender
import logging.Logging
import org.w3c.dom.HTMLDivElement
import pimonitor.authentication.signup.SignUp
import pimonitor.evaluation.business.BusinessContainer
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val client = client(jso {
        appId = "test-client"
        simulationTime = 3000
    })
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