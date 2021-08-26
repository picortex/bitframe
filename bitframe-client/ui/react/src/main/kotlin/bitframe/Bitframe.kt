package bitframe

import bitframe.authentication.login.LoginPage
import bitframe.panel.Panel
import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import react.RProps
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import reakt.DrawerState
import reakt.ModuleRoute
import reakt.NavigationDrawer
import styled.styledDiv

fun RBuilder.Bitframe(client: BitframeService, version: String) {
    val routes = listOf(
        ModuleRoute<RProps>("/", listOf(), "") {
            LoginPage(client.authentication, version)
        },
        ModuleRoute<RProps>("/panel", listOf(), "") {
            Panel()
        }
    )
    browserRouter {
        switch {
            for (r in routes) route(r.path, exact = true, render = r.render)
            styledDiv { +"Whoops, Not Found" }
        }
    }
}