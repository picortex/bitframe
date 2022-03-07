package pimonitor.client.picortex

import bitframe.client.ServiceConfigKtor
import bitframe.core.RequestBody
import later.Later
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.picortex.AcceptPicortexInviteRawParams

class PiCortexDashboardServiceKtor(
    override val config: ServiceConfigKtor
) : PiCortexDashboardService(config) {
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteRawParams>): Later<AcceptPicortexInviteParams> {
        TODO("Not yet implemented")
    }
}