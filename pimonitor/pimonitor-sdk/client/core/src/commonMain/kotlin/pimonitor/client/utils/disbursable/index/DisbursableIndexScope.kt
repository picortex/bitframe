package pimonitor.client.utils.disbursable.index

import bitframe.client.IndexMicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.utils.disbursables.DisbursableService
import pimonitor.core.utils.disbursables.Disbursable

internal fun <D : Disbursable> DisburasableIndexScope(config: UIScopeConfig<DisbursableService<D>>) = IndexMicroScope {
    viewModel(DisbursableIndexViewModel(config))
}