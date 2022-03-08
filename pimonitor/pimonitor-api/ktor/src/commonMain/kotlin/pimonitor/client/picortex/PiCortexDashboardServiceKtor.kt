package pimonitor.client.picortex

import bitframe.client.ServiceConfigKtor
import bitframe.core.RequestBody
import later.Later
import pimonitor.client.sage.SageDashboardService
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.picortex.AcceptPicortexInviteRawParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.sage.AcceptSageOneInviteRawParams

class PiCortexDashboardServiceKtor(
    override val config: ServiceConfigKtor
) : PiCortexDashboardService(config) {
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptPicortexInviteParams>): Later<AcceptPicortexInviteParams> {
        TODO("Not yet implemented")
    }
}