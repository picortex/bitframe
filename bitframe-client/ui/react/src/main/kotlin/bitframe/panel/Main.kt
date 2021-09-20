package bitframe.panel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.route
import react.router.dom.switch
import bitframe.PanelPageRoute
import bitframe.renderers.Renderer
import reakt.*
import styled.styledDiv
import useViewModelState

private external interface PanelProps : Props {
    var controller: MutableStateFlow<DrawerState>
    var moduleRenderers: Map<String, Renderer>
    var scope: PanelScope
}

private val Panel = fc<PanelProps> { props ->
    val controller = props.controller
    when (val state = useViewModelState(props.scope.viewModel)) {
        is PanelState.Loading -> LoadingBox(state.message, 80)
        is PanelState.Panel -> NavigationDrawer(
            drawerState = controller,
            drawer = { Drawer(controller, state) },
            content = { Body(controller,props.moduleRenderers) }
        )
    }
}

fun RBuilder.Panel(moduleRenderers: Map<String, Renderer>) = child(Panel) {
    attrs.controller = MutableStateFlow(DrawerState.Opened)
    attrs.moduleRenderers = moduleRenderers
    attrs.scope = PanelScope()
}