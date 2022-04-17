package pimonitor.client.utils.disbursables

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentRawParams
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams
import presenters.table.Row

interface DisbursablesIntent {
    data class LoadAllDisbursables(val businessId: String?) : DisbursablesIntent

//    data class ShowCreateInvestmentForm(val business: MonitoredBusinessBasicInfo?, val params: InvestmentRawParams?) : DisbursablesIntent
//    data class SendCreateInvestmentForm(val params: InvestmentRawParams) : DisbursablesIntent
//
//    data class ShowUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams?) : DisbursablesIntent
//    data class SendUpdateInvestmentForm(val investment: InvestmentSummary, val params: InvestmentRawParams) : DisbursablesIntent

    data class ShowDisbursementForm(val disbursable: Disbursable, val params: DisbursementRawParams?) : DisbursablesIntent
    data class SendDisbursementForm(val disbursable: Disbursable, val params: DisbursementRawParams) : DisbursablesIntent

    data class ShowDeleteOneDisbursableDialog(val disbursable: Disbursable) : DisbursablesIntent
    data class SendDeleteOneDisbursableIntent(val disbursable: Disbursable) : DisbursablesIntent

    data class ShowDeleteManyDisbursablesDialog(val disbursables: Array<Row<Disbursable>>) : DisbursablesIntent
    data class SendDeleteManyDisbursablesIntent(val disbursables: Array<Disbursable>) : DisbursablesIntent
}