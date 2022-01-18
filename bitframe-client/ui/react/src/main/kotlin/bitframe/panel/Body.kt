package bitframe.panel

import bitframe.PanelPageRoute
import bitframe.renderers.Renderer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.RBuilder
import react.createElement
import react.router.Route
import react.router.Routes
import reakt.DrawerState
import reakt.NavigationAppBar
import reakt.*
import styled.styledDiv

private fun defaultRenderers(): Map<String, Renderer> = mapOf(
    "/dashboard" to { styledDiv { +"Dashboard will show here" } },
    "/users" to { styledDiv { +"Users will be displayed here" } }
)

internal fun RBuilder.Body(
    controller: MutableStateFlow<DrawerState>,
    moduleRenderers: Map<String, Renderer>
) = styledDiv {
    val allRenderers = moduleRenderers.toMutableMap().apply {
        putAll(defaultRenderers())
    }

    NavigationAppBar(
        drawerController = controller,
        left = {
            +"Dashboard"
        }
    )

    Surface(margin = 0.5.em) {
        Routes {
            for ((path, renderer) in allRenderers) {
                Route {
                    attrs.path = "${PanelPageRoute}$path"
                    renderer()
                }
            }
            styledDiv { +"Excuse me, are you lost?" }
        }
    }
}