package bitframe.client.panel

import bitframe.client.BitframeReactAppScope
import bitframe.authentication.signin.SignInPage
import bitframe.renderers.Renderer
import kotlinx.coroutines.flow.MutableStateFlow
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.DrawerState
import reakt.LoadingBox
import reakt.NavigationDrawer
import useViewModelState

private external interface PanelProps : Props {
    var version: String
    var controller: MutableStateFlow<DrawerState>
    var moduleRenderers: Map<String, Renderer>
    var scope: BitframeReactAppScope
}

private val Panel = fc<PanelProps> { props ->
    val controller = props.controller
    val scope = props.scope.panel
    useEffectOnce { scope.initPanel() }
    when (val state = useViewModelState(scope.viewModel)) {
        is PanelState.Loading -> LoadingBox(state.message, 80)
        is PanelState.Panel -> NavigationDrawer(
            drawerState = controller,
            drawer = { Drawer(controller, state) },
            content = { Body(controller, props.scope, props.moduleRenderers) }
        )
        PanelState.Login -> SignInPage(props.scope, props.version)
    }
}

fun RBuilder.Panel(scope: BitframeReactAppScope, moduleRenderers: Map<String, Renderer>, version: String) = child(Panel) {
    attrs.controller = MutableStateFlow(DrawerState.Opened)
    attrs.moduleRenderers = moduleRenderers
    attrs.scope = scope
    attrs.version = version
}