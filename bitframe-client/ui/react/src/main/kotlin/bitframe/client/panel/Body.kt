package bitframe.client.panel

import bitframe.client.BitframeReactAppScope
import bitframe.renderers.Renderer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.RBuilder
import react.createElement
import react.router.*
import reakt.ContainedButton
import reakt.DrawerState
import reakt.NavigationAppBar
import reakt.Surface
import styled.styledDiv

private fun defaultRenderers(): Map<String, Renderer> = mapOf(
    "/dashboard" to { styledDiv { +"Dashboard will show here" } },
    "/users" to { styledDiv { +"Users will be displayed here" } }
)

internal fun RBuilder.Body(
    controller: MutableStateFlow<DrawerState>,
    scope: BitframeReactAppScope,
    moduleRenderers: Map<String, Renderer>
) = styledDiv {
    NavigationAppBar(
        drawerController = controller,
        left = {
            +"Dashboard"
        },
        right = {
            ContainedButton(
                name = "Logout",
                onClick = { scope.signOut() }
            )
        }
    )

    Surface(margin = 0.5.em) {
        Routes {
            for ((path, renderer) in moduleRenderers + defaultRenderers()) Route {
                attrs.path = path // "${PanelPageRoute}$path"
                attrs.element = createElement { renderer() }
            }
        }
    }
}