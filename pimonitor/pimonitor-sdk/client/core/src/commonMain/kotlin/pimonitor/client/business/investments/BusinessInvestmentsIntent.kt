package pimonitor.client.business.investments

import pimonitor.client.business.investments.params.CreateInvestmentsRawFormParams
import pimonitor.client.business.utils.disbursements.DisbursementRawFormParams
import pimonitor.core.business.investments.Investment

sealed class BusinessInvestmentsIntent {
    data class LoadAllInvestments(val businessId: String) : BusinessInvestmentsIntent()

    data class ShowCreateInvestmentForm(val businessId: String) : BusinessInvestmentsIntent()
    data class SendCreateInvestmentForm(val params: CreateInvestmentsRawFormParams) : BusinessInvestmentsIntent()

    data class ShowCreateDisbursementForm(val investment: Investment) : BusinessInvestmentsIntent()
    data class SendCreateDisbursementForm(val params: DisbursementRawFormParams) : BusinessInvestmentsIntent()

    object ExitDialog : BusinessInvestmentsIntent()
}