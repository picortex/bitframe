package pimonitor.client.intervention.index

import bitframe.client.UIScopeConfig
import pimonitor.client.interventions.InterventionService
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.utils.disbursable.index.DisburasableIndexScope

internal fun InterventionIndexScope(config: UIScopeConfig<InterventionService>) = DisburasableIndexScope(config)