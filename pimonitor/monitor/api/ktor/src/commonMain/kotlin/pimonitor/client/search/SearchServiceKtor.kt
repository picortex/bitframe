package pimonitor.client.search

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.search.SearchParams
import pimonitor.core.search.SearchResult
import response.decodeResponseFromString

class SearchServiceKtor(
    override val config: ServiceConfigKtor
) : SearchService(config) {
    private val client get() = config.http
    private val json get() = config.json

    override fun search(rb: RequestBody.Authorized<SearchParams>): Later<List<SearchResult>> = config.scope.later {
        val res = client.post(config.pathV1.search) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(SearchResult.serializer()), res.bodyAsText()).response().toInteroperableList()
    }
}