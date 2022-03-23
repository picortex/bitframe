package bitframe.server.rest

import bitframe.core.rest.AuthEndpoint
import bitframe.server.ServiceConfig

val ServiceConfig.pathV1 get() = AuthEndpoint.Server("v1")