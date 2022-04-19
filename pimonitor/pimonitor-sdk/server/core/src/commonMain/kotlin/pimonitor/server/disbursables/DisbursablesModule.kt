package pimonitor.server.disbursables

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.DisbursableEndpoint
import pimonitor.core.utils.disbursables.DisbursableServiceDaod
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.server.utils.pathV1

open class DisbursablesModule<out D : Disbursable, out DS : DisbursableSummary>(
    open val controller: DisbursablesController<D, DS>,
    private val serializer: KSerializer<@UnsafeVariance D>,
    private val summarySerializer: KSerializer<@UnsafeVariance DS>,
    val path: DisbursableEndpoint,
    disbursableName: String
) {
    val disbursableActions: List<Action> = listOf(
        Action("Load an $disbursableName", mapOf(), HttpRoute(HttpMethod.Post, path.load) {
            controller.load(it)
        }),
        Action("Load all ${disbursableName}s", mapOf(), HttpRoute(HttpMethod.Post, path.all) {
            controller.all(it)
        }),
        Action("Create a disbursement", mapOf(), HttpRoute(HttpMethod.Post, path.disbursementCreate) {
            controller.createDisbursement(it)
        }),
        Action("update a disbursement", mapOf(), HttpRoute(HttpMethod.Post, path.disbursementUpdate) {
            controller.updateDisbursement(it)
        }),
        Action("delete a disbursement", mapOf(), HttpRoute(HttpMethod.Post, path.disbursementDelete) {
            controller.delete(it)
        })
    )
}