package pimonitor

import applikation.konfig
import bitframe.Bitframe
import client
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.extensions.By
import kotlinx.extensions.get
import logging.Logger
import org.w3c.dom.HTMLDivElement
import pimonitor.authentication.signup.SignUp
import pimonitor.evaluation.businesses.BusinessContainer
import pimonitor.evaluation.businesses.InviteBusiness
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val konfig = konfig()
    val client = client {
        appId = "test-client"
        url = "https://dev.picortex.com"//konfig["url"]?.toString() ?: window.location.origin
        disableViewModelLogs = true
    }
    val config = PiMonitorViewModelConfig(service = client)
    val version: String by konfig()
    Bitframe(
        config = config,
        pages = mapOf(
            "/authentication/sign-up" to { SignUp(config) },
            "/invite/:uid" to { InviteBusiness(config, it.match.params["uid"]) }
        ),
        sections = mapOf(
            "/evaluation/business" to { BusinessContainer(config) }
        ),
        version = version
    )
}