package pimonitor.client.business.investments.params

import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.investments.params.CreateInvestmentDisbursementParams
import validation.BlankFieldException
import validation.requiredNotBlank

fun DisbursementRawFormParams.toCreateInvestmentDisbursementParams(
    investmentId: String
) = CreateInvestmentDisbursementParams(
    investmentId = investmentId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("investmentId"),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)