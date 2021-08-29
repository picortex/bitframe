package pimonitor

import applikation.konfig
import bitframe.Bitframe
import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration
import kotlinx.browser.document
import reakt.setContent

fun main() = document.getElementById("root").setContent {
    val configuration = TestClientConfiguration(appId = "test-client", simulationTime = 2000)
    val client = BitframeTestClient(configuration)
    val version: String by konfig()
    Bitframe(client = client, version = version)
}