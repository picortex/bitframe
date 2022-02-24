package pimonitor.server.profile

import bitframe.core.RequestBody
import bitframe.core.profile.ProfileDaodService
import bitframe.core.profile.params.ChangePasswordParams
import bitframe.server.http.HttpRequest
import bitframe.server.http.HttpResponse
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import response.response

class ProfileController(
    val service: ProfileDaodService
) {
    val json get() = service.config.json
    suspend fun changePassword(req: HttpRequest): HttpResponse = response {
        val rb = json.decodeFromString<RequestBody.Authorized<ChangePasswordParams>>(req.compulsoryBody())
        resolve(service.changePassword(rb).await())
    }.toHttpResponse()
}