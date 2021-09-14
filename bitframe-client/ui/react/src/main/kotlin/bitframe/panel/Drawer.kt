package bitframe.panel

import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import reakt.*

internal fun RBuilder.Drawer(controller: MutableStateFlow<DrawerState>) = NavPane(
    drawerController = controller,
    moduleGroups = mapOf(
        "Evaluation" to listOf(
            NavMenu("Businesses", link = "/panel/businesses", FaBuilding, scope = ""),
        ),
        "Administrator" to listOf(
            NavMenu("Users", link = "/panel/users", FaUser, scope = "")
        ),
        "Personal" to listOf(
            NavMenu("Settings", link = "/panel/settings", FaTools, scope = ""),
            NavMenu("Profile", link = "/panel/profile", FaUser, scope = "")
        )
    ),
    header = {
        CompanyHeader(
            logoPath = "https://res.cloudinary.com/dc3mzhqp1/image/upload/v1597218653/PiLogos/logo_2x_eema7k.png",
            userName = "Test Monitor"
        )
    }
)