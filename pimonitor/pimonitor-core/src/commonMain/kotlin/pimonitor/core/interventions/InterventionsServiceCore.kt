@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.interventions

import bitframe.core.Identified
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.interventions.params.InterventionParams
import pimonitor.core.utils.disbursables.DisbursableServiceCore
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface InterventionsServiceCore : DisbursableServiceCore<Intervention, InterventionSummary> {
    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<InterventionParams>): Later<Intervention>

    @JsName("_ignore_update")
    fun update(rb: RequestBody.Authorized<Identified<InterventionParams>>): Later<Intervention>

    @JsName("_ignore_createGoal")
    fun createGoal(rb: RequestBody.Authorized<DisbursableDisbursementParams>): Later<Goal>

    @JsName("_ignore_deleteGoal")
    fun deleteGoal(rb: RequestBody.Authorized<Identified<Array<String>>>): Later<List<Goal>>

    @JsName("_ignore_updateGoal")
    fun updateGoal(rb: RequestBody.Authorized<Identified<DisbursableDisbursementParams>>): Later<Goal>
}