package bitframe

import applikation.konfig
import client
import kotlinx.browser.document
import kotlinx.extensions.By
import kotlinx.extensions.get
import org.w3c.dom.HTMLDivElement
import bitframe.authentication.signup.SignUp
import bitframe.evaluation.businesses.BusinessContainer
import bitframe.evaluation.businesses.InviteBusiness
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val client = client {
        appId = "test-client"
         url = "http://localhost:8080"
    }
    val version: String by konfig()
    Bitframe(
        client = client,
        pages = mapOf(
            "/authentication/sign-up" to { SignUp(client) },
            "/invite/:uid" to { InviteBusiness(client, it.match.params["uid"]) }
        ),
        sections = mapOf(
            "/evaluation/business" to { BusinessContainer(client) }
        ),
        version = version
    )
}