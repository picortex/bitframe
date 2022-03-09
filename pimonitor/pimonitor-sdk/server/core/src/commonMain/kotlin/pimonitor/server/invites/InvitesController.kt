package pimonitor.server.invites

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.server.PiMonitorService
import response.response

class InvitesController(
    val service: PiMonitorService
) {
    private val json get() = service.config.json
    suspend fun acceptSageInvite(req: HttpRequest) = response {
        val params = json.decodeFromString<RequestBody.UnAuthorized<AcceptSageOneInviteParams>>(req.compulsoryBody())
        resolve(service.sage.acceptInvite(params).await())
    }.toHttpResponse()

    suspend fun acceptPicortexInvite(req: HttpRequest) = response {
        val params = json.decodeFromString<RequestBody.UnAuthorized<AcceptPicortexInviteParams>>(req.compulsoryBody())
        resolve(service.picortex.acceptInvite(params).await())
    }.toHttpResponse()
}