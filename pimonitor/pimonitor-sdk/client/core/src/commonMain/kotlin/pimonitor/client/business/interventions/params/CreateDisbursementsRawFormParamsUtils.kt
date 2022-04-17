package pimonitor.client.business.interventions.params

import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams
import validation.BlankFieldException
import validation.requiredNotBlank

fun DisbursementRawParams.toCreateInterventionDisbursementParams(
    interventionId: String
) = CreateInterventionDisbursementParams(
    interventionId = interventionId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("investmentId"),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)