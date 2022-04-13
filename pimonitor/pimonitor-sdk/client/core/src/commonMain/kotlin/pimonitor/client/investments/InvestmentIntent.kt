package pimonitor.client.investments

import pimonitor.client.business.investments.params.CreateInvestmentsRawFormParams
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.InvestmentSummary
import presenters.table.Row

sealed class InvestmentIntent {
    object LoadAllInvestments : InvestmentIntent()

    data class ShowCreateInvestmentForm(val business: MonitoredBusinessBasicInfo?) : InvestmentIntent()
    data class SendCreateInvestmentForm(val params: CreateInvestmentsRawFormParams) : InvestmentIntent()

    data class ShowEditInvestmentForm(val investment: InvestmentSummary) : InvestmentIntent()
    data class SendEditInvestmentForm(val investment: InvestmentSummary, val params: CreateInvestmentsRawFormParams) : InvestmentIntent()

    data class ShowDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawFormParams?) : InvestmentIntent()
    data class SendDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawFormParams) : InvestmentIntent()

    data class ShowDeleteOneInvestmentDialog(val investment: InvestmentSummary) : InvestmentIntent()
    data class SendDeleteOneInvestmentIntent(val investment: InvestmentSummary) : InvestmentIntent()

    data class ShowDeleteManyInvestmentsDialog(val investments: Array<Row<InvestmentSummary>>) : InvestmentIntent()
    data class SendDeleteManyInvestmentsIntent(val investments: Array<InvestmentSummary>) : InvestmentIntent()
}