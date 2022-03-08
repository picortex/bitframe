package pimonitor.client.picortex

import bitframe.client.ServiceConfigKtor
import bitframe.core.RequestBody
import later.Later
import pimonitor.client.sage.SageDashboardService
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.sage.AcceptSageOneInviteRawParams

class PiCortexDashboardServiceKtor(
    override val config: ServiceConfigKtor
) : SageDashboardService(config) {
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteRawParams>): Later<AcceptSageOneInviteParams> {
        TODO("Not yet implemented")
    }
}