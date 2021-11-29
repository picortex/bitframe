package bitframe

import bitframe.authentication.signin.SignInPage
import bitframe.client.BitframeService
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import bitframe.renderers.Renderer
import react.RBuilder
import react.createElement
import react.router.dom.BrowserRouter
import react.router.dom.Route
import react.router.dom.Switch
import styled.styledDiv

const val SignInPageRoute = "/authentication/sign-in/"
const val SignUpPageRoute = "/authentication/sign-up/"
const val PanelPageRoute = "/panel"
const val HomeRoute = "/"

private fun defaultRenderers(
    client: BitframeService,
    moduleRenderers: Map<String, Renderer>,
    version: String
): Map<String, Renderer> = mapOf(
    SignInPageRoute to { SignInPage(client, version) },
    PanelPageRoute to { Panel(client, moduleRenderers) },
    HomeRoute to { LandingPage(version) }
)

fun RBuilder.Bitframe(
    client: BitframeService,
    pages: Map<String, Renderer> = mapOf(),
    sections: Map<String, Renderer> = mapOf(),
    version: String
) = BrowserRouter {
    val allRouteRenderers = pages.toMutableMap() + defaultRenderers(client, sections, version)
    Switch {
        for ((path, renderer) in allRouteRenderers) Route {
            attrs.path = arrayOf(path)
            attrs.render = { props -> createElement { renderer(props) } }
        }
        styledDiv { +"Whoops, Not Found" }
    }
}