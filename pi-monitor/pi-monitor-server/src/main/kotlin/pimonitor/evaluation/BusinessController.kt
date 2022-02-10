package pimonitor.evaluation

import bitframe.server.http.HttpRequest
import bitframe.server.http.toHttpResponse
import pimonitor.businesses.Business
import pimonitor.server.PiMonitorService
import response.response

class BusinessController(val service: PiMonitorService) {

    suspend fun create(req: HttpRequest) = response<Business> {
        TODO()
//        val reqBody = Json.decodeFromString<CreateBusinessRequestBody>(
//            req.body ?: reject("Make sure to pass a request body to your request")
//        )
//        resolve(service.businesses.create(reqBody.params, reqBody.monitor).await())
    }.toHttpResponse()

    suspend fun all() = response<List<Business>> {
        TODO()
//        resolve(service.businesses.all().await())
    }.toHttpResponse()
}