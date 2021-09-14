package bitframe

import bitframe.authentication.login.SignInPage
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import react.RBuilder
import react.router.dom.*
import styled.styledDiv

open class AbstractModuleRoute(
    val permits: List<String>,
    val path: String,
    val scope: String,
    val render: RBuilder.(props: RouteResultProps) -> Unit
)

fun ModuleRoute(
    path: String,
    permits: List<String>,
    scope: String,
    builder: RBuilder.(props: RouteResultProps) -> Unit
) = AbstractModuleRoute(permits, path, scope, builder)

internal const val SignInPageRoute = "/authentication/sign-in/"
internal const val SignUpPageRoute = "/authentication/sign-up/"
internal const val PanelPageRoute = "/panel"

private fun routes(client: BitframeService, version: String) = listOf(
    ModuleRoute(SignInPageRoute, listOf(), "") {
        SignInPage(client.signIn, version)
    },
    ModuleRoute(PanelPageRoute, listOf(), "") {
        Panel()
    },
    ModuleRoute("/", listOf(), "") {
        LandingPage(version)
    }
)

fun RBuilder.Bitframe(
    client: BitframeService,
    registration: RBuilder.(props: RouteResultProps) -> Unit,
    version: String
) = browserRouter {
    val routes = routes(client, version)
    switch {
        route(SignUpPageRoute, exact = true, strict = true, render = registration)
        for (r in routes) route(r.path, render = r.render)
        styledDiv { +"Whoops, Not Found" }
    }
}