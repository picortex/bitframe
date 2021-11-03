package pimonitor.client

import bitframe.client.BitframeServiceConfig
import bitframe.events.EventBus

interface PiMonitorServiceConfig : BitframeServiceConfig {
    val bus: EventBus
}