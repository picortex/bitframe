package bitframe

import bitframe.authentication.login.SignInPage
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import bitframe.renderers.Renderer
import react.RBuilder
import react.router.dom.*
import styled.styledDiv

internal const val SignInPageRoute = "/authentication/sign-in/"
internal const val SignUpPageRoute = "/authentication/sign-up/"
internal const val PanelPageRoute = "/panel"
internal const val HomeRoute = "/"

private fun defaultRenderers(
    client: BitframeService,
    moduleRenderers: Map<String, Renderer>,
    version: String
): Map<String, Renderer> = mapOf(
    SignInPageRoute to { SignInPage(client.signIn, version) },
    PanelPageRoute to { Panel(moduleRenderers) },
    HomeRoute to { LandingPage(version) }
)

fun RBuilder.Bitframe(
    client: BitframeService,
    routeRenderers: Map<String, Renderer> = mapOf(),
    moduleRenderers: Map<String, Renderer> = mapOf(),
    version: String
) = browserRouter {
    val allRouteRenderers = routeRenderers.toMutableMap().apply {
        putAll(defaultRenderers(client, moduleRenderers, version))
    }
    switch {
        for ((path, renderer) in allRouteRenderers) route(path, render = renderer)
        styledDiv { +"Whoops, Not Found" }
    }
}