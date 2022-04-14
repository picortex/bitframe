package pimonitor.client.investments

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentRawParams
import pimonitor.core.utils.disbursements.params.DisbursementRawParams
import presenters.table.Row

sealed class InvestmentsIntent {
    data class LoadAllInvestments(val businessId: String?) : InvestmentsIntent()

    data class ShowCreateInvestmentForm(val business: MonitoredBusinessBasicInfo?, val params: InvestmentRawParams?) : InvestmentsIntent()
    data class SendCreateInvestmentForm(val params: InvestmentRawParams) : InvestmentsIntent()

    data class ShowUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams?) : InvestmentsIntent()
    data class SendUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams) : InvestmentsIntent()

    data class ShowDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawParams?) : InvestmentsIntent()
    data class SendDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawParams) : InvestmentsIntent()

    data class ShowDeleteOneInvestmentDialog(val investment: InvestmentSummary) : InvestmentsIntent()
    data class SendDeleteOneInvestmentIntent(val investment: InvestmentSummary) : InvestmentsIntent()

    data class ShowDeleteManyInvestmentsDialog(val investments: Array<Row<InvestmentSummary>>) : InvestmentsIntent()
    data class SendDeleteManyInvestmentsIntent(val investments: Array<InvestmentSummary>) : InvestmentsIntent()
}