package bitframe

import bitframe.authentication.signin.SignInPage
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import bitframe.renderers.Renderer
import react.RBuilder
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
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
    SignInPageRoute to { SignInPage(client.signIn, version) },
    PanelPageRoute to { Panel(client,moduleRenderers) },
    HomeRoute to { LandingPage(version) }
)

fun RBuilder.Bitframe(
    client: BitframeService,
    pages: Map<String, Renderer> = mapOf(),
    modules: Map<String, Renderer> = mapOf(),
    version: String
) = browserRouter {
    val allRouteRenderers = pages.toMutableMap().apply {
        putAll(defaultRenderers(client, modules, version))
    }
    switch {
        for ((path, renderer) in allRouteRenderers) route(path, render = renderer)
        styledDiv { +"Whoops, Not Found" }
    }
}