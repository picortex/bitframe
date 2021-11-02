package bitframe.panel

import bitframe.BitframeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.route
import react.router.dom.switch
import bitframe.PanelPageRoute
import bitframe.SignInPageRoute
import bitframe.renderers.Renderer
import react.router.dom.Redirect
import react.useEffectOnce
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
    val scope = props.scope
    useEffectOnce { scope.initPanel() }
    when (val state = useViewModelState(scope.viewModel)) {
        is PanelState.Loading -> LoadingBox(state.message, 80)
        is PanelState.Panel -> NavigationDrawer(
            drawerState = controller,
            drawer = { Drawer(controller, state) },
            content = { Body(controller, props.moduleRenderers) }
        )
        PanelState.Login -> Redirect {
            attrs.to = SignInPageRoute
        }
    }
}

fun RBuilder.Panel(client: BitframeService, moduleRenderers: Map<String, Renderer>) = child(Panel) {
    attrs.controller = MutableStateFlow(DrawerState.Opened)
    attrs.moduleRenderers = moduleRenderers
    attrs.scope = PanelScope(client)
}