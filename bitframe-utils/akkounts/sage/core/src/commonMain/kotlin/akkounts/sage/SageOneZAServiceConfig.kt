package akkounts.sage

import kotlin.jvm.JvmOverloads

data class SageOneZAServiceConfig @JvmOverloads constructor(
    val apiKey: String,
    val environment: SageOneZAService.Environment = SageOneZAService.Environment.PROD
)