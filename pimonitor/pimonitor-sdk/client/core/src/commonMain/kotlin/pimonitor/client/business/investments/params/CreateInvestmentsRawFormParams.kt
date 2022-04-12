package pimonitor.client.business.investments.params

import pimonitor.core.investments.params.CreateInvestmentsParams
import validation.BlankFieldException
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentsRawFormParams {
    val name: String
    val type: String
    val source: String
    val amount: String
    val date: String
    val details: String
}

fun CreateInvestmentsRawFormParams.toValidatedParams(businessId: String) = CreateInvestmentsParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("businessId"),
    name = requiredNotBlank(::name),
    type = requiredNotBlank(::type),
    source = requiredNotBlank(::source),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
    details = requiredNotBlank(::details),
)