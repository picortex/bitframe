package bitframe

import bitframe.authentication.signin.SignInPage
import bitframe.api.BitframeService
import bitframe.client.BitframeViewModelConfig
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
    config: BitframeViewModelConfig,
    moduleRenderers: Map<String, Renderer>,
    version: String
): Map<String, Renderer> = mapOf(
    SignInPageRoute to { SignInPage(config, version) },
    PanelPageRoute to { Panel(config, moduleRenderers) },
    HomeRoute to { LandingPage(version) }
)

fun RBuilder.Bitframe(
    config: BitframeViewModelConfig,
    pages: Map<String, Renderer> = mapOf(),
    sections: Map<String, Renderer> = mapOf(),
    version: String
) = BrowserRouter {
    val allRouteRenderers = pages.toMutableMap() + defaultRenderers(config, sections, version)
    Switch {
        for ((path, renderer) in allRouteRenderers) Route {
            attrs.path = arrayOf(path)
            attrs.render = { props -> createElement { renderer(props) } }
        }
        styledDiv { +"Whoops, Not Found" }
    }
}