package pimonitor.client.business.investments

import pimonitor.client.business.utils.disbursements.CreateDisbursementRawFormParams
import pimonitor.core.business.investments.Investment
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual

sealed class BusinessInvestmentsIntent {
    data class LoadAllInvestments(val businessId: String) : BusinessInvestmentsIntent()

    data class ShowCreateInvestmentForm(val businessId: String) : BusinessInvestmentsIntent()
    data class SendCreateInvestmentForm(val params: CreateInvestmentsRawParamsContextual) : BusinessInvestmentsIntent()

    data class ShowCreateDisbursementForm(val investment: Investment) : BusinessInvestmentsIntent()
    data class SendCreateDisbursementForm(val params: CreateDisbursementRawFormParams) : BusinessInvestmentsIntent()

    object ExitDialog : BusinessInvestmentsIntent()
}