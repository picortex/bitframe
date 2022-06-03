package pimonitor.client.investment.disbursements

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.utils.disbursable.disbursements.DisbursementsIntents
import pimonitor.client.utils.disbursable.disbursements.DisbursementsViewModel

internal fun InvestmentDisbursementScope(config: UIScopeConfig<InvestmentsService>) = MicroScope {
    viewModel(DisbursementsViewModel(config))
    intents(DisbursementsIntents(viewModel))
}