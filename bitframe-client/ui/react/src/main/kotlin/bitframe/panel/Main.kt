package bitframe.panel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.em
import react.RBuilder
import reakt.*
import styled.styledDiv

fun RBuilder.Panel(controller: MutableStateFlow<DrawerState>) = NavigationDrawer(
    drawer = {
        NavPane(
            drawerController = controller,
            moduleGroups = mapOf(),
            header = {
                CompanyHeader(
                    logoPath = "https://res.cloudinary.com/dc3mzhqp1/image/upload/v1597218653/PiLogos/logo_2x_eema7k.png",
                    userName = "Test User"
                )
            }
        )
    },
    content = {
        NavigationAppBar(
            drawerController = controller,
            left = {
                +"Dashboard"
            }
        )

        Surface(margin = 0.5.em) {
            styledDiv { +"Content Will Go Here" }
        }
    },
    drawerState = controller
)