package pimonitor.server.business.interventions

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.business.investments.BusinessInvestmentsServiceDaod
import pimonitor.core.business.investments.params.CreateInvestmentsParams
import response.response

class BusinessInterventionsController(
    val service: BusinessInvestmentsServiceDaod
) {
    private val json get() = service.config.json
    suspend fun create(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<CreateInvestmentsParams>>(req.compulsoryBody())
        resolve(service.capture(rb).await())
    }.toHttpResponse()
}