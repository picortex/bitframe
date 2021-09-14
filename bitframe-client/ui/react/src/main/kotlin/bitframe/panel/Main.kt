package bitframe.panel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.route
import react.router.dom.switch
import bitframe.PanelPageRoute
import reakt.*
import styled.styledDiv

private external interface PanelProps : Props {
    var controller: MutableStateFlow<DrawerState>
}

private val Panel = fc<PanelProps> { props ->
    val controller = props.controller

    NavigationDrawer(
        drawerState = controller,
        drawer = { Drawer(controller) },
        content = { Body(controller) }
    )
}

fun RBuilder.Panel() = child(Panel) {
    attrs.controller = MutableStateFlow(DrawerState.Opened)
}