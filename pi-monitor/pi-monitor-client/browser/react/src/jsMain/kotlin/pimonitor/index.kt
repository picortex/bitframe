package pimonitor

import applikation.konfig
import bitframe.Bitframe
import cache.BrowserCache
import kotlinext.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.transition
import kotlinx.extensions.By
import kotlinx.extensions.get
import live.Live
import live.MutableLive
import live.mutableLiveOf
import live.value
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get
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
//        url = "https://dev.picortex.com"
//        url = konfig["url"]?.toString() ?: window.location.origin
        url = "http://localhost:8080"
        serviceLoggers = jso {
            console = true
        }
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