package pimonitor.client.investments

import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.investments.InvestmentSummary
import presenters.table.Row

sealed class InvestmentIntent {
    object LoadAllInvestments : InvestmentIntent()

    data class ShowDisbursementForm(val investment: InvestmentSummary) : InvestmentIntent()
    data class SendDisbursementForm(val investment: InvestmentSummary, val params: DisbursementRawFormParams) : InvestmentIntent()

    data class ShowEditInvestmentForm(val investment: InvestmentSummary) : InvestmentIntent()
    data class SendEditInvestmentForm(val investment: InvestmentSummary) : InvestmentIntent()

    data class ShowDeleteOneInvestmentDialog(val investment: InvestmentSummary) : InvestmentIntent()
    data class ShowDeleteManyInvestmentDialog(val investment: Array<Row<InvestmentSummary>>) : InvestmentIntent()
}