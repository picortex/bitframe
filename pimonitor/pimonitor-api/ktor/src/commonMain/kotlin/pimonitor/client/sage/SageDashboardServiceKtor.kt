package pimonitor.client.sage

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import pimonitor.core.RestPath
import pimonitor.core.sage.AcceptSageOneInviteParams
import response.decodeResponseFromString

class SageDashboardServiceKtor(
    override val config: ServiceConfigKtor
) : SageDashboardService(config) {
    private val client get() = config.http
    private val json get() = config.json
    override fun acceptInvite(rb: RequestBody.UnAuthorized<AcceptSageOneInviteParams>) = config.scope.later {
        val res = client.post("${config.url}/${RestPath.inviteAcceptSage}") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(AcceptSageOneInviteParams.serializer(), res.bodyAsText()).response()
    }
}