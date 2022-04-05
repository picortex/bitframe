package pimonitor.core.business.investments.params

import kotlinx.collections.interoperable.listOf
import pimonitor.core.business.investments.Investment
import pimonitor.core.business.investments.InvestmentHistory
import validation.BlankFieldException
import validation.requiredNotBlank
import validation.requiredPositive
import validation.requiredPositiveDouble
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentsRawParams {
    val businessId: String
    val name: String
    val type: String
    val source: String
    val amount: Double
    val date: Double
    val details: String
}

fun CreateInvestmentsRawParams.toInvestment(created: InvestmentHistory.Created) = Investment(
    businessId = businessId,
    name = name,
    type = type,
    history = listOf(created),
    disbursements = listOf(),
    source = source,
    amount = amount,
    date = date,
    details = details
)

fun CreateInvestmentsRawParams.toValidatedCreateInvestmentsParams() = CreateInvestmentsParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("businessId"),
    name = requiredNotBlank(::name),
    type = requiredNotBlank(::type),
    source = requiredNotBlank(::source),
    amount = requiredPositiveDouble(::amount),
    date = requiredPositiveDouble(::date),
    details = requiredNotBlank(::details),
)