package pimonitor.server.contacts

import bitframe.core.RequestBody
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import kotlinx.serialization.decodeFromString
import later.await
import pimonitor.core.contacts.ContactsServiceDaod
import pimonitor.core.contacts.ContactsFilter
import response.response

class ContactsController(
    private  val service: ContactsServiceDaod
) {
    internal val config get() = service.config
    private val json get() = config.json

    suspend fun all(req: HttpRequest) = response {
        val rb = json.decodeFromString<RequestBody.Authorized<ContactsFilter>>(req.compulsoryBody())
        resolve(service.all(rb).await())
    }.toHttpResponse()
}