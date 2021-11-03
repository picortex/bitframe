package bitframe.client

import bitframe.service.client.config.ServiceConfig
import cache.Cache

interface BitframeServiceConfig : ServiceConfig {
    val cache: Cache
}