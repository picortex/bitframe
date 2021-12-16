@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import pimonitor.PiMonitorScope

fun scope(config: ServiceConfiguration) = PiMonitorScope(client(config))