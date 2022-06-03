package akkounts.sage

import java.util.*

fun Properties.toSageOneZAServiceConfig(): SageOneZAServiceConfig {
    val key = this["api_key"]?.toString() ?: throw IllegalArgumentException("Missing sae.api_key in properties file")
    val env = if (this["environment"]?.toString()?.lowercase()?.contains("test") == true) {
        SageOneZAService.Environment.TEST
    } else {
        SageOneZAService.Environment.PROD
    }
    return SageOneZAServiceConfig(key, env)
}