package pimonitor.client.business.investments

import pimonitor.core.business.investments.CaptureInvestmentsRawParams

sealed class BusinessInvestmentsIntent {
    data class LoadAllInvestments(val businessId: String) : BusinessInvestmentsIntent()

    data class ShowCreateInvestmentForm(val businessId: String) : BusinessInvestmentsIntent()
    data class SendCreateInvestmentForm(val params: CaptureInvestmentsRawParams) : BusinessInvestmentsIntent()

    data class ShowCreateDisbursementForm(val investmentId: String) : BusinessInvestmentsIntent()
    data class SendCreateDisbursementForm(val investmentId: String) : BusinessInvestmentsIntent()

    object ExitDialog : BusinessInvestmentsIntent()
}