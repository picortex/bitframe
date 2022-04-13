package pimonitor.client.business.investments

import pimonitor.client.business.investments.params.InvestmentsRawParams
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.investments.Investment

sealed class BusinessInvestmentsIntent {
    data class LoadAllInvestments(val businessId: String) : BusinessInvestmentsIntent()

    data class ShowCreateInvestmentForm(val businessId: String) : BusinessInvestmentsIntent()
    data class SendCreateInvestmentForm(val params: InvestmentsRawParams) : BusinessInvestmentsIntent()

    data class ShowCreateDisbursementForm(val investment: Investment) : BusinessInvestmentsIntent()
    data class SendCreateDisbursementForm(val params: DisbursementRawFormParams) : BusinessInvestmentsIntent()

    object ExitDialog : BusinessInvestmentsIntent()
}