package bitframe

import bitframe.authentication.signin.SignInPage
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import bitframe.renderers.Renderer
import react.RBuilder
import react.createElement
import react.dom.div
import react.router.PathRouteProps
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import styled.styledDiv

const val SignInPageRoute = "/authentication/sign-in/"
const val SignUpPageRoute = "/authentication/sign-up/"
const val PanelPageRoute = "/panel/*"
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
    Routes {
        for ((path, renderer) in pages + defaultRenderers(scope, sections, version)) Route {
            attrs.path = path
            attrs.element = createElement { renderer() }
        }
    }
}