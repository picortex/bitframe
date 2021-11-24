package bitframe.evaluation

import bitframe.response.response.response
import bitframe.server.http.HttpRequest
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await
import bitframe.monitored.CreateBusinessRequestBody
import bitframe.server.PiMonitorService

class BusinessController(val service: PiMonitorService) {

    suspend fun create(req: HttpRequest) = response {
        val reqBody = Json.decodeFromString<CreateBusinessRequestBody>(
            req.body ?: reject("Make sure to pass a request body to your request")
        )
        resolve(service.businesses.create(reqBody.params, reqBody.monitor).await())
    }.toHttpResponse()

    suspend fun all() = response {
        resolve(service.businesses.all().await())
    }.toHttpResponse()
}