@file:JsExport

package pimonitor.evaluation.businesses

import later.Later
import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

abstract class BusinessesService {
    abstract fun all(): Later<List<MonitoredBusiness>>
}