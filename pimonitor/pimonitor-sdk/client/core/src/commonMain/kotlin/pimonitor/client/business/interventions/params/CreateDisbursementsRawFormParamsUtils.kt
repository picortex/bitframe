package pimonitor.client.business.interventions.params

import datetime.SimpleDateTime
import pimonitor.client.business.utils.disbursements.CreateDisbursementRawFormParams
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import validation.BlankFieldException
import validation.requiredNotBlank

fun CreateDisbursementRawFormParams.toCreateInterventionDisbursementParams(
    interventionId: String
) = CreateInterventionDisbursementParams(
    interventionId = interventionId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("investmentId"),
    amount = requiredNotBlank(::amount).toDouble(),
    date = SimpleDateTime.parseDate(requiredNotBlank(::date)).timeStampInMillis,
)