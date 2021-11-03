package bitframe

import bitframe.client.BitframeServiceConfig
import bitframe.events.EventBus
import bitframe.service.client.config.KtorClientConfiguration

interface BitframeKtorServiceConfig : BitframeServiceConfig, KtorClientConfiguration {
    val bus: EventBus
}