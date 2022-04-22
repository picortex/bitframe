package pimonitor.server.interventions

import bitframe.core.Identified
import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.interventions.InterventionsServiceDaod
import pimonitor.core.interventions.params.InterventionParams
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.InvestmentsServiceDaod
import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.server.disbursables.DisbursablesController
import response.response

class InterventionsController(
    override val service: InterventionsServiceDaod
) : DisbursablesController<Intervention, InterventionSummary>(
    service, Intervention.serializer(), InterventionSummary.serializer()
) {
    private val json get() = service.config.json

    suspend fun createIntervention(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<InterventionParams>>(req.compulsoryBody())
        resolve(service.create(rb).await())
    }.toHttpResponse()

    suspend fun updateIntervention(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<Identified<InterventionParams>>>(req.compulsoryBody())
        resolve(service.update(rb).await())
    }.toHttpResponse()
}