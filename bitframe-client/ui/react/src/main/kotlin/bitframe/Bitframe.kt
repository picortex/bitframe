package bitframe

import bitframe.authentication.login.LoginPage
import bitframe.panel.Panel
import react.RBuilder
import react.router.dom.RouteResultProps
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
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

fun RBuilder.Bitframe(client: BitframeService, version: String) {
    val routes = listOf(
        ModuleRoute("/", listOf(), "") {
            LoginPage(client.authentication, version)
        },
        ModuleRoute("/panel", listOf(), "") {
            Panel()
        }
    )
    browserRouter {
        switch {
            for (r in routes) route(r.path, exact = true, strict = true, render = r.render)
            styledDiv { +"Whoops, Not Found" }
        }
    }
}