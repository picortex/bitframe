package pimonitor.client.utils

import bitframe.client.ServiceConfigKtor
import pimonitor.core.RestEndpoint

val ServiceConfigKtor.path: RestEndpoint get() = RestEndpoint.Client(url)