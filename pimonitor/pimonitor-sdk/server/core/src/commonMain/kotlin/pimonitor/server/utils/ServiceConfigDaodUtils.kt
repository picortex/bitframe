package pimonitor.server.utils

import bitframe.core.ServiceConfigDaod
import pimonitor.core.RestEndpoint

val ServiceConfigDaod.path: RestEndpoint get() = RestEndpoint.Server()