@file:JsExport

package pimonitor.client.businesses

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.collections.Table
import kotlin.js.JsExport

sealed class BusinessesState {
    data class Loading(val message: String) : BusinessesState() {
        val loading = true
    }

    data class Businesses(
        val table: Table<MonitoredBusinessSummary>,
        val dialog: BusinessesDialog
    ) : BusinessesState()

    data class Failure(val cause: Throwable, val message: String? = cause.message) : BusinessesState() {
        val failure = true
    }

    data class Success(val message: String) : BusinessesState() {
        val success = true
    }
}
