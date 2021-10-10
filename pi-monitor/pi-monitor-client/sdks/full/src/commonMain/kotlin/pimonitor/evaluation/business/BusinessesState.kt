@file:JsExport

package pimonitor.evaluation.business

import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

sealed class BusinessesState {
    data class Loading(val message: String) : BusinessesState()
    data class Businesses(val data: List<MonitoredBusiness>) : BusinessesState()
    data class BusinessForm(val form: Boolean = true) : BusinessesState()
    data class Failure(val cause: Throwable, val message: String? = cause.message) : BusinessesState()
    data class Success(val message: String) : BusinessesState()
}
