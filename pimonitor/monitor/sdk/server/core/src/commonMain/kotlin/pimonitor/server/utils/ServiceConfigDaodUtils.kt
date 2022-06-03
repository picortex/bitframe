package pimonitor.server.utils

import bitframe.core.ServiceConfigDaod
import pimonitor.core.rest.Endpoint

val ServiceConfigDaod.pathV1: Endpoint get() = Endpoint.Server("v1")