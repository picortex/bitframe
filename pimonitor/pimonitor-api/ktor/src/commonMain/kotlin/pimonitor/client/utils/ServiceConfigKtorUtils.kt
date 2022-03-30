package pimonitor.client.utils

import bitframe.client.ServiceConfigKtor
import pimonitor.core.rest.Endpoint

val ServiceConfigKtor.pathV1: Endpoint get() = Endpoint.Client(url, "v1")