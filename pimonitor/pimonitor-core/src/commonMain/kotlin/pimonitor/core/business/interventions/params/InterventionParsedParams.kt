package pimonitor.core.business.interventions.params

import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import pimonitor.core.business.interventions.Intervention
import pimonitor.core.business.interventions.InterventionHistory

@Serializable
data class InterventionParsedParams(
    val businessId: String,
    val name: String,
    val date: Date,
    val deadline: Date,
    val amount: Money,
    val recommendations: String,
) {
    fun toIntervention(created: InterventionHistory.Created) = Intervention(
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