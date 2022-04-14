package pimonitor.client.business.investments.params

import pimonitor.core.investments.params.InvestmentDisbursementParams
import pimonitor.core.utils.disbursements.params.DisbursementRawParams
import validation.BlankFieldException
import validation.requiredNotBlank

fun DisbursementRawParams.toCreateInvestmentDisbursementParams(
    investmentId: String
) = InvestmentDisbursementParams(
    investmentId = investmentId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("investmentId"),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)