package bitframe.panel

import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInService
import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import reakt.*

internal fun RBuilder.Drawer(
    controller: MutableStateFlow<DrawerState>,
    state: PanelState.Panel
) = NavPane(
    drawerController = controller,
    moduleGroups = state.modules.toModuleGroups(),
    header = {
        CompanyHeader(
            logoPath = "https://res.cloudinary.com/dc3mzhqp1/image/upload/v1597218653/PiLogos/logo_2x_eema7k.png",
            userName = (SignInService.current as? Session.SignedIn)?.user?.name ?: "Unknown User"
        )
    }
)

private fun List<UIModuleGroup>.toModuleGroups() = associate { group ->
    val scopeName = group.name.lowercase().replace(" ", "-")
    group.name to group.modules.map { module ->
        val moduleName = module.name.lowercase().replace(" ", "-")
        NavMenu(module.name, link = "/panel/$scopeName/$moduleName", icon = FaUser, "")
    }
}