package pimonitor.core.business.investments

import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CaptureInvestmentsRawParams {
    val businessId: String
    val name: String
    val type: String
    val source: String
    val amount: Double
    val date: Double
    val details: String
}

fun CaptureInvestmentsRawParams.toValidatedCaptureInvestmentsParams(
    businessId: String = this.businessId
) = CaptureInvestmentsParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: "Business id must be provided",
    name = requiredNotBlank(::name),
    type = requiredNotBlank(::type),
    source = requiredNotBlank(::source),
    amount = requiredPositive(::amount),
    date = requiredPositive(::date),
    details = requiredNotBlank(::details),
)