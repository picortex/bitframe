@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import pimonitor.PiMonitorReactScope

fun scope(config: ServiceConfiguration) = PiMonitorReactScope(client(config))