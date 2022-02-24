package bitframe.client

import bitframe.client.signin.SignInPage
import bitframe.client.BitframeReactAppScope
import bitframe.client.landing.LandingPage
import bitframe.client.panel.Panel
import bitframe.client.renderers.Renderer
import react.RBuilder
import react.createElement
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

const val SignInPageRoute = "/authentication/sign-in/"
const val SignUpPageRoute = "/authentication/sign-up/"
const val PanelPageRoute = "/panel/*"
const val HomeRoute = "/"

private fun defaultRenderers(
    scope: BitframeReactAppScope<*>,
    moduleRenderers: Map<String, Renderer>,
    version: String
): Map<String, Renderer> = mapOf(
    SignInPageRoute to { SignInPage(scope, version) },
    PanelPageRoute to { Panel(scope, moduleRenderers, version) },
    HomeRoute to { LandingPage(version) }
)

fun RBuilder.Bitframe(
    scope: BitframeReactAppScope<*>,
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