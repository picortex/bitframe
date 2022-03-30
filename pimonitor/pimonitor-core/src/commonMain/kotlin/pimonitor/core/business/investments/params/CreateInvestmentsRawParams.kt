package pimonitor.core.business.investments.params

import kotlinx.collections.interoperable.listOf
import pimonitor.core.business.investments.Investment
import pimonitor.core.business.investments.InvestmentHistory
import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentsRawParams : CreateInvestmentsRawParamsContextual {
    val businessId: String
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

fun CreateInvestmentsRawParams.toValidatedCreateInvestmentsParams() = toValidatedCreateInvestmentsParams(this.businessId)