package pimonitor.client.business.operations

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig

internal fun BusinessOperationsScope(
    config: UIScopeConfig<BusinessOperationsService>,
) = MicroScope {
    viewModel(BusinessOperationsViewModel(config))
    intents(BusinessOperationsIntents(viewModel))
}