package pimonitor.client.intervention.disbursements

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.interventions.InterventionService
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.utils.disbursable.disbursements.DisbursementsIntents
import pimonitor.client.utils.disbursable.disbursements.DisbursementsViewModel

internal fun InterventionDisbursementScope(config: UIScopeConfig<InterventionService>) = MicroScope {
    viewModel(DisbursementsViewModel(config))
    intents(DisbursementsIntents(viewModel))
}