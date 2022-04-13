@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.interventions

import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.business.interventions.params.CreateGoalParams
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.business.interventions.params.InterventionParams
import pimonitor.core.utils.disbursements.Disbursement
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface BusinessInterventionsServiceCore {
    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<InterventionParams>): Later<Intervention>

    /**
     * @param rb takes in a [RequestBody.Authorized] of a businessId
     */
    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<String>): Later<List<Intervention>>

    @JsName("_ignore_disburse")
    fun disburse(rb: RequestBody.Authorized<CreateInterventionDisbursementParams>): Later<Disbursement>

    @JsName("_ignore_setGoal")
    fun addGoal(rb: RequestBody.Authorized<CreateGoalParams>): Later<Any>
}