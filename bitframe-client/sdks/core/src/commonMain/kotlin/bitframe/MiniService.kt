package bitframe

import bitframe.authentication.ClientConfiguration
import platform.ExecutionEnvironment
import platform.Platform
import kotlin.js.JsExport

interface MiniService {
    val platform: ExecutionEnvironment get() = Platform
    val config: ClientConfiguration
}