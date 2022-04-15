package pimonitor.client.investment.disbursements

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.utils.disbursements.DisbursementsIntents
import pimonitor.client.utils.disbursements.DisbursementsViewModel

fun InvestmentDisbursementScope(config: UIScopeConfig<InvestmentsService>) = MicroScope {
    viewModel(DisbursementsViewModel(config))
    intents(DisbursementsIntents(viewModel))
}