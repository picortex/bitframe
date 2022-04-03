package pimonitor.client.business.investments.params

import datetime.SimpleDateTime
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementParams
import validation.BlankFieldException
import validation.requiredNotBlank

fun DisbursementRawFormParams.toCreateInvestmentDisbursementParams(
    investmentId: String
) = CreateInvestmentDisbursementParams(
    investmentId = investmentId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("investmentId"),
    amount = requiredNotBlank(::amount).toDouble(),
    date = SimpleDateTime.parseDate(requiredNotBlank(::date)).timeStampInMillis,
)