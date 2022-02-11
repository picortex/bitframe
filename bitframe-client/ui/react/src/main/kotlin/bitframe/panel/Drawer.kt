package bitframe.panel

import bitframe.service.Session
import kotlinx.coroutines.flow.MutableStateFlow
import react.RBuilder
import reakt.*

internal fun RBuilder.Drawer(
    controller: MutableStateFlow<DrawerState>,
    state: PanelState.Panel
) = NavPane(
    drawerController = controller,
    moduleGroups = emptyMap(),
    header = {
        CompanyHeader(
            logoPath = "https://res.cloudinary.com/dc3mzhqp1/image/upload/v1597218653/PiLogos/logo_2x_eema7k.png",
            userName = (state.session as? Session.SignedIn)?.user?.name ?: "Unknown User"
        )
    }
)