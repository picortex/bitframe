package pimonitor

//import applikation.konfig
//import bitframe.Bitframe
//import client
//import kotlinx.browser.document
//import kotlinx.browser.window
//import kotlinx.extensions.By
//import kotlinx.extensions.get
//import org.w3c.dom.HTMLDivElement
//import pimonitor.authentication.signup.SignUp
//import pimonitor.evaluation.businesses.BusinessContainer
//import pimonitor.evaluation.businesses.InviteBusiness
//import reakt.setContent
//
//fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
//    val konfig = konfig()
//    val client = client {
//        appId = "test-client"
//        url = konfig["url"]?.toString() ?: window.location.origin
//    }
//    val version: String by konfig()
//    Bitframe(
//        client = client,
//        pages = mapOf(
//            "/authentication/sign-up" to { SignUp(client) },
//            "/invite/:uid" to { InviteBusiness(client, it.match.params["uid"]) }
//        ),
//        sections = mapOf(
//            "/evaluation/business" to { BusinessContainer(client) }
//        ),
//        version = version
//    )
//}

fun main() {
    console.log("Test")
}