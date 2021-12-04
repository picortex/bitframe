package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.response.response.response
import bitframe.server.http.HttpRequest
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await
import pimonitor.server.monitors.MonitorsService

class MonitorsController(
    val service: MonitorsService
) {
    suspend fun get(req: HttpRequest) = response {
        val uid by req.queryParameters
        resolve(service.load(uid).await())
    }.toHttpResponse()

    suspend fun getWithUser(req: HttpRequest) = response {
        val userRef = Json.decodeFromString<UserRef>(
            req.body ?: error("Make sure to pass in the userRef her as a body")
        )
        resolve(service.monitor(userRef).await())
    }.toHttpResponse()
}