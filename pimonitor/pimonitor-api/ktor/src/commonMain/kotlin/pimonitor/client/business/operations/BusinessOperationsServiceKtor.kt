package pimonitor.client.business.operations

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.operations.BusinessOperationsServiceCore
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import response.decodeResponseFromString

class BusinessOperationsServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessOperationsService(config), BusinessOperationsServiceCore {
    val client get() = config.http
    val path get() = config.pathV1
    val json get() = config.json
    override fun dashboard(rb: RequestBody.Authorized<LoadInfoParsedParams>) = config.scope.later {
        val req = client.post(path.businessOperationalDashboard) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(InfoResults.serializer(OperationalDifferenceBoard.serializer()), req.bodyAsText()).response()
    }
}