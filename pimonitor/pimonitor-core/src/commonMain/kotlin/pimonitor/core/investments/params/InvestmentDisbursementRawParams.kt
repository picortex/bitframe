package pimonitor.core.investments.params

import pimonitor.core.business.utils.disbursements.DisbursementRawParams
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InvestmentDisbursementRawParams : DisbursementRawParams {
    val investmentId: String
}

fun InvestmentDisbursementRawParams.toValidatedParams() = InvestmentDisbursementParams(
    investmentId = requiredNotBlank(::investmentId),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)