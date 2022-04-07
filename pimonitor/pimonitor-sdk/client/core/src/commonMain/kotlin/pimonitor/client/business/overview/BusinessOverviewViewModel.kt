package pimonitor.client.business.overview

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import pimonitor.client.PiMonitorApi
import presenters.cases.State
import viewmodel.ViewModel

class BusinessOverviewViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessOverviewIntent, State<Int>>(State.Loading("Testing")) {
    override fun CoroutineScope.execute(i: BusinessOverviewIntent): Any {
        TODO("Not yet implemented")
    }
}