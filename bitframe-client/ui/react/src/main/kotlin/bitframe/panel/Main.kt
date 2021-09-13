package bitframe.panel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.RBuilder
import react.Props
import react.State
import reakt.*
import styled.styledDiv


@JsExport
class Panel private constructor() : Component<Props, State>() {
    private val controller = MutableStateFlow(DrawerState.Opened)

    private fun RBuilder.Drawer() = NavPane(
        drawerController = controller,
        moduleGroups = mapOf(),
        header = {
            CompanyHeader(
                logoPath = "https://res.cloudinary.com/dc3mzhqp1/image/upload/v1597218653/PiLogos/logo_2x_eema7k.png",
                userName = "Test Monitor"
            )
        }
    )

    private fun RBuilder.Body() = styledDiv {
        NavigationAppBar(
            drawerController = controller,
            left = {
                +"Dashboard"
            }
        )

        Surface(margin = 0.5.em) {
            styledDiv { +"Content Will Go Here" }
        }
    }

    override fun RBuilder.render(): dynamic = NavigationDrawer(
        drawerState = controller,
        drawer = { Drawer() },
        content = { Body() }
    )
}

fun RBuilder.Panel() = child(Panel::class) {}