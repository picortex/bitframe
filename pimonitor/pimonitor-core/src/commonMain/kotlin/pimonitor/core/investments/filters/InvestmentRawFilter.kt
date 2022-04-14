package pimonitor.core.investments.filters

import kotlin.js.JsExport

@JsExport
interface InvestmentRawFilter {
    val businessId: String?
}

fun InvestmentRawFilter?.toValidatedFilter() = InvestmentFilter(
    businessId = this?.businessId
)