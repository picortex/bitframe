package pimonitor.client.sage

import bitframe.client.ServiceConfigKtor
import bitframe.core.RequestBody
import later.Later
import pimonitor.core.sage.AcceptSageOneInviteParams

class SageDashboardServiceKtor(
    override val config: ServiceConfigKtor
) : SageDashboardService(config) {
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>): Later<AcceptSageOneInviteParams> {
        TODO("Not yet implemented")
    }
}