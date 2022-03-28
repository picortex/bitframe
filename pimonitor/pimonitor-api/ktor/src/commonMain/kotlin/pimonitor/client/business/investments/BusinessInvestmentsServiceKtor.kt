package pimonitor.client.business.investments

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.decodeFromString
import later.Later
import later.later
import pimonitor.client.utils.pathV1
import pimonitor.core.business.investments.CaptureInvestmentsParams
import pimonitor.core.business.investments.Investment
import response.decodeResponseFromString

open class BusinessInvestmentsServiceKtor(
    private val config: ServiceConfigKtor
) : BusinessInvestmentsService(config) {
    val path get() = config.pathV1
    val client get() = config.http
    val json get() = config.json
    override fun capture(rb: RequestBody.Authorized<CaptureInvestmentsParams>) = config.scope.later {
        val res = client.post(path.businessInvestmentsCapture) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun all(rb: RequestBody.Authorized<String>) = config.scope.later {
        val res = client.post(path.businessInvestmentsAll) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(ListSerializer(Investment.serializer()), res.bodyAsText()).response().toInteroperableList()
    }
}