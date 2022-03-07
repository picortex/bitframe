package pimonitor.client.picortex

import bitframe.client.KtorServiceConfig
import later.Later
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.picortex.AcceptPicortexInviteRawParams

class PiCortexDashboardServiceKtor(
    val config: KtorServiceConfig
) : PiCortexDashboardService {
    override fun acceptInvite(params: AcceptPicortexInviteRawParams): Later<AcceptPicortexInviteParams> {
        TODO("Not yet implemented")
    }
}