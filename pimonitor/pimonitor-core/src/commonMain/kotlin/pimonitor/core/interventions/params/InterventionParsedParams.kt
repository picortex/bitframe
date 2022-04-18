package pimonitor.core.interventions.params

import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionHistory

@Serializable
data class InterventionParsedParams(
    val businessId: String,
    val name: String,
    val date: Date,
    val deadline: Date,
    val amount: Money,
    val recommendations: String,
) {
    fun toIntervention(created: InterventionHistory.Created, owningSpaceId: String) = Intervention(
        owningSpaceId = owningSpaceId,
        businessId = businessId,
        name = name,
        history = listOf(created),
        disbursements = listOf(),
        amount = amount,
        date = date,
        deadline = deadline,
        goals = listOf(),
        recommendations = recommendations
    )
}