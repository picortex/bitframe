package pimonitor.core.business.investments.params

import pimonitor.core.business.utils.disbursements.CreateDisbursementRawParamsContextual
import validation.BlankFieldException
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentDisbursementRawParams : CreateDisbursementRawParamsContextual {
    val investmentId: String
}

fun CreateDisbursementRawParamsContextual.toValidatedCreateDisbursementParams(
    investmentId: String
) = CreateInvestmentDisbursementParams(
    investmentId = investmentId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("investmentId"),
    amount = requiredPositive(::amount),
    date = requiredPositive(::date),
)

fun CreateInvestmentDisbursementRawParams.toValidatedCreateDisbursementParams() = toValidatedCreateDisbursementParams(investmentId)