package pimonitor.client.contacts

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.later
import pimonitor.core.contacts.ContactsFilter
import pimonitor.core.search.SearchResult
import response.decodeResponseFromString

class ContactsServiceKtor(
    override val config: ServiceConfigKtor
) : ContactsService(config) {
    private val client get() = config.http
    private val json get() = config.json
    private val basePath = "/api/contacts"
    override fun all(rb: RequestBody.Authorized<ContactsFilter>) = config.scope.later {
        val result = client.post("${config.url}$basePath/all") {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(
            dataSerializer = ListSerializer(SearchResult.ContactPersonSummary.serializer()),
            json = result.bodyAsText()
        ).response().toInteroperableList()
    }
}