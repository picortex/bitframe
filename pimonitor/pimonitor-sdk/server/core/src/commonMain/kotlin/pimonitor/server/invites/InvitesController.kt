package pimonitor.server.invites

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
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
        resolve(service.invites.acceptSageInvite(params).await())
    }.toHttpResponse()

    suspend fun acceptPicortexInvite(req: HttpRequest) = response {
        val params = json.decodeFromString<RequestBody.UnAuthorized<AcceptPicortexInviteParams>>(req.compulsoryBody())
        resolve(service.invites.acceptPiCortexInvite(params).await())
    }.toHttpResponse()

    suspend fun send(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InviteToShareReportsParams>>(req.compulsoryBody())
        resolve(service.invites.send(rb).await())
    }.toHttpResponse()

    suspend fun load(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.UnAuthorized<String>>(req.compulsoryBody())
        resolve(service.invites.load(rb).await())
    }.toHttpResponse()

    suspend fun defaultInviteMessage(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InviteMessageParams>>(req.compulsoryBody())
        resolve(service.invites.defaultInviteMessage(rb).await())
    }.toHttpResponse()
}