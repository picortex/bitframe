package pimonitor.client.business.investments

import pimonitor.client.business.investments.params.InvestmentsRawParams
import pimonitor.core.investments.Investment
import pimonitor.core.utils.disbursements.params.DisbursementRawParams

sealed class BusinessInvestmentsIntent {
    data class LoadAllInvestments(val businessId: String) : BusinessInvestmentsIntent()

    data class ShowCreateInvestmentForm(val businessId: String) : BusinessInvestmentsIntent()
    data class SendCreateInvestmentForm(val params: InvestmentsRawParams) : BusinessInvestmentsIntent()

    data class ShowCreateDisbursementForm(val investment: Investment) : BusinessInvestmentsIntent()
    data class SendCreateDisbursementForm(val params: DisbursementRawParams) : BusinessInvestmentsIntent()

    object ExitDialog : BusinessInvestmentsIntent()
}