@file:JsExport

package pimonitor.evaluation.business

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.evaluation.business.BusinessesIntent.*
import pimonitor.evaulation.business.BusinessService
import pimonitor.evaluation.business.BusinessesIntent as Intent
import pimonitor.evaluation.business.BusinessesState as State

class BusinessViewModel(
    val service: BusinessService
) : ViewModel<Intent, State>(State.Loading("Loading business")) {
    override fun CoroutineScope.execute(i: Intent) = when (i) {
        LoadBusinesses -> loadBusiness()
    }

    private fun CoroutineScope.loadBusiness() = launch {
        flow {
            emit(State.Loading("Loading business, please wait . . ."))
            val businesses = service.all().await()
            emit(State.Businesses(businesses))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }
}