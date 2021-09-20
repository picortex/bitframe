@file:JsExport

package pimonitor.evaluation.business

import pimonitor.Monitor
import kotlin.js.JsExport

sealed class BusinessState {
    data class Loading(val message: String) : BusinessState()
    data class Businesses(val data: List<Monitor.Business>) : BusinessState()
    data class Failure(val cause: Throwable, val message: String? = cause.message) : BusinessState()
    data class Success(val message: String) : BusinessState()
}
