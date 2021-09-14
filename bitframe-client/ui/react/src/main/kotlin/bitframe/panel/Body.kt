package bitframe.panel

import bitframe.PanelPageRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.RBuilder
import react.router.dom.route
import react.router.dom.switch
import reakt.DrawerState
import reakt.NavigationAppBar
import reakt.*
import styled.styledDiv

fun RBuilder.Body(controller: MutableStateFlow<DrawerState>) = styledDiv {
    NavigationAppBar(
        drawerController = controller,
        left = {
            +"Dashboard"
        }
    )

    Surface(margin = 0.5.em) {
        switch {
            route("${PanelPageRoute}/dashboard") {
                styledDiv { +"Dashboard will show here" }
            }
            route("${PanelPageRoute}/users") {
                styledDiv { +"Users will be displayed here" }
            }
            route("${PanelPageRoute}/businesses") {
                styledDiv { +"Business will show here" }
            }
            styledDiv { +"Excuse me, are you lost?" }
        }
    }
}