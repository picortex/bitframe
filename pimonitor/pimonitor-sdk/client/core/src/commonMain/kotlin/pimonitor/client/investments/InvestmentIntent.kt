package pimonitor.client.investments

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentRawParams
import pimonitor.core.utils.disbursements.params.DisbursementRawParams
import presenters.table.Row

sealed class InvestmentIntent {
    data class LoadAllInvestments(val businessId: String?) : InvestmentIntent()

    data class ShowCreateInvestmentForm(val business: MonitoredBusinessBasicInfo?, val params: InvestmentRawParams?) : InvestmentIntent()
    data class SendCreateInvestmentForm(val params: InvestmentRawParams) : InvestmentIntent()

    data class ShowUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams?) : InvestmentIntent()
    data class SendUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams) : InvestmentIntent()

    data class ShowDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawParams?) : InvestmentIntent()
    data class SendDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawParams) : InvestmentIntent()

    data class ShowDeleteOneInvestmentDialog(val investment: InvestmentSummary) : InvestmentIntent()
    data class SendDeleteOneInvestmentIntent(val investment: InvestmentSummary) : InvestmentIntent()

    data class ShowDeleteManyInvestmentsDialog(val investments: Array<Row<InvestmentSummary>>) : InvestmentIntent()
    data class SendDeleteManyInvestmentsIntent(val investments: Array<InvestmentSummary>) : InvestmentIntent()
}