package pimonitor.core.business.investments.params

import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentsRawParamsContextual {
    val name: String
    val type: String
    val source: String
    val amount: Double
    val date: Double
    val details: String
}

fun CreateInvestmentsRawParamsContextual.toValidatedCreateInvestmentsParams(
    businessId: String
) = CreateInvestmentsParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: "Business id must be provided",
    name = requiredNotBlank(::name),
    type = requiredNotBlank(::type),
    source = requiredNotBlank(::source),
    amount = requiredPositive(::amount),
    date = requiredPositive(::date),
    details = requiredNotBlank(::details),
)