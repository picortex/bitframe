package pimonitor.client.investments

import pimonitor.client.utils.disbursables.DisbursablesIntent
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentRawParams

sealed class InvestmentsIntent : DisbursablesIntent {
    data class ShowCreateInvestmentForm(val business: MonitoredBusinessBasicInfo?, val params: InvestmentRawParams?) : InvestmentsIntent()
    data class SendCreateInvestmentForm(val params: InvestmentRawParams) : InvestmentsIntent()

    data class ShowUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams?) : InvestmentsIntent()
    data class SendUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams) : InvestmentsIntent()
}