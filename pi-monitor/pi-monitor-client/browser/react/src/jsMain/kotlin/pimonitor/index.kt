package pimonitor

import applikation.konfig
import bitframe.Bitframe
import kotlinext.js.jso
import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.transition
import kotlinx.extensions.By
import kotlinx.extensions.get
import org.w3c.dom.HTMLDivElement
import pimonitor.authentication.signup.SignUp
import pimonitor.evaluation.businesses.BusinessContainer
import pimonitor.evaluation.businesses.InviteBusiness
import reakt.setContent
import scope
import styled.css
import styled.styledDiv

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val konfig = konfig()
    val scope = scope {
        appId = "test-client"
        url = "https://dev.picortex.com"//konfig["url"]?.toString() ?: window.location.origin
        viewModel = jso {
            recoveryTime = undefined
            logging = jso {
                console = true
            }
        }
    }
    val version: String by konfig()
    Bitframe(
        scope = scope,
        pages = mapOf(
            "/authentication/sign-up" to { SignUp(scope) },
            "/invite/:uid" to { InviteBusiness(scope, null) }
        ),
        sections = mapOf(
            "/evaluation/business" to { BusinessContainer(scope) }
        ),
        version = version
    )
}