package pimonitor.core.picortex

import later.Later

interface PiCortexDashboardServiceCore {
    fun acceptInvite(params: AcceptPicortexInviteRawParams) : Later<AcceptPicortexInviteParams>
}