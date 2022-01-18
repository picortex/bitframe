package bitframe

import bitframe.authentication.signin.SignInPage
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import bitframe.renderers.Renderer
import react.RBuilder
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import styled.styledDiv

const val SignInPageRoute = "/authentication/sign-in/"
const val SignUpPageRoute = "/authentication/sign-up/"
const val PanelPageRoute = "/panel"
const val HomeRoute = "/"

private fun defaultRenderers(
    scope: BitframeReactScope,
    moduleRenderers: Map<String, Renderer>,
    version: String
): Map<String, Renderer> = mapOf(
    SignInPageRoute to { SignInPage(scope, version) },
    PanelPageRoute to { Panel(scope, moduleRenderers, version) },
    HomeRoute to { LandingPage(version) }
)

fun RBuilder.Bitframe(
    scope: BitframeReactScope,
    pages: Map<String, Renderer> = mapOf(),
    sections: Map<String, Renderer> = mapOf(),
    version: String
) = BrowserRouter {
    val allRouteRenderers = pages.toMutableMap() + defaultRenderers(scope, sections, version)
    Routes {
        for ((path, renderer) in allRouteRenderers) Route {
            attrs.path = path
            renderer()
        }
        styledDiv { +"Whoops, Not Found" }
    }
}