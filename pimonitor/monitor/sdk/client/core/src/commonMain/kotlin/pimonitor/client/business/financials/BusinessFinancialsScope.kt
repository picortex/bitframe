package pimonitor.client.business.financials

import bitframe.client.MicroScope
import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import kotlin.js.JsExport
import pimonitor.client.business.financials.BusinessFinancialsIntent as Intent

internal fun BusinessFinancialsScope(
    config: UIScopeConfig<BusinessFinancialsService>
) = MicroScope {
    viewModel(BusinessFinancialsViewModel(config))
    intents(BusinessFinancialsIntents(viewModel))
}