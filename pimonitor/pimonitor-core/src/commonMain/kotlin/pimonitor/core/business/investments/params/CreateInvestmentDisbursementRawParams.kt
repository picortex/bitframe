package pimonitor.core.business.investments.params

import pimonitor.core.business.utils.disbursements.CreateDisbursementRawParams
import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentDisbursementRawParams : CreateDisbursementRawParams {
    val investmentId: String
}

fun CreateInvestmentDisbursementRawParams.toValidatedParams() = CreateInvestmentDisbursementParams(
    investmentId = requiredNotBlank(::investmentId),
    amount = requiredPositive(::amount),
    date = requiredPositive(::date),
)