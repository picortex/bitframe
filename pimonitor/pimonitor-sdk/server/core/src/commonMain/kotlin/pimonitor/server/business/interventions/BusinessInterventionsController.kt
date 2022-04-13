package pimonitor.server.business.interventions

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.interventions.BusinessInterventionsServiceDaod
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.business.interventions.params.InterventionParams
import response.response

class BusinessInterventionsController(
    val service: BusinessInterventionsServiceDaod
) {
    private val json get() = service.config.json
    suspend fun create(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InterventionParams>>(req.compulsoryBody())
        resolve(service.create(rb).await())
    }.toHttpResponse()

    suspend fun disburse(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<CreateInterventionDisbursementParams>>(req.compulsoryBody())
        resolve(service.disburse(rb).await())
    }.toHttpResponse()

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<String>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse()
}