package pimonitor.core.picortex

import java.util.*

fun Properties.toPicortexServiceConfig(): PiCortexServiceConfig {
    val env = if (this["environment"]?.toString() == "staging") {
        PiCortexServiceConfig.Environment.Staging
    } else {
        PiCortexServiceConfig.Environment.Production
    }
    return PiCortexServiceConfig(
        environment = env
    )
}