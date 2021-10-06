@file:JsExport

package pimonitor.evaluation.businesses

import later.Later
import pimonitor.Monitor
import kotlin.js.JsExport

abstract class BusinessService {
    abstract fun all(): Later<List<Monitor.Business>>
}