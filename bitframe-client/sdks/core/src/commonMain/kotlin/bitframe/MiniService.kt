package bitframe

import bitframe.authentication.ClientConfiguration
import platform.ExecutionEnvironment
import platform.Platform

interface MiniService {
    val platform: ExecutionEnvironment get() = Platform
    val config: ClientConfiguration
}