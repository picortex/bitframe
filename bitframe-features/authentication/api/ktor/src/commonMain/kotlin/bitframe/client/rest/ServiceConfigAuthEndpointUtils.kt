package bitframe.client.rest

import bitframe.client.ServiceConfigKtor
import bitframe.core.rest.AuthEndpoint

internal val ServiceConfigKtor.pathV1 get() = AuthEndpoint.Client(url, "v1")