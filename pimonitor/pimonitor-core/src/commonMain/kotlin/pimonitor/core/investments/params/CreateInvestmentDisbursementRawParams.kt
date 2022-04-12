package pimonitor.core.investments.params

import pimonitor.core.business.utils.disbursements.DisbursementRawParams
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentDisbursementRawParams : DisbursementRawParams {
    val investmentId: String
}

fun CreateInvestmentDisbursementRawParams.toValidatedParams() = CreateInvestmentDisbursementParams(
    investmentId = requiredNotBlank(::investmentId),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)