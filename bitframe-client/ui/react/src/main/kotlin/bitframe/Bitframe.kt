package bitframe

import bitframe.authentication.login.SignInPage
import bitframe.landing.LandingPage
import bitframe.panel.Panel
import react.Props
import react.RBuilder
import react.fc
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

internal const val SignInPageRoute = "/authentication/sign-in"
internal const val SignUpPageRoute = "/authentication/sign-up"

private fun routes(client: BitframeService, version: String) = listOf(
    ModuleRoute("/", listOf(), "") {
        LandingPage(version)
    },
    ModuleRoute(SignInPageRoute, listOf(), "") {
        SignInPage(client.authentication, version)
    },
    ModuleRoute("/panel", listOf(), "") {
        Panel()
    }
)

fun RBuilder.Bitframe(client: BitframeService, version: String) = browserRouter {
    val routes = routes(client, version)
    switch {
        for (r in routes) route(r.path, exact = true, strict = true, render = r.render)
        styledDiv { +"Whoops, Not Found" }
    }
}