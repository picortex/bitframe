package bitframe.service

import bitframe.service.config.ClientConfiguration
import platform.ExecutionEnvironment
import platform.Platform

interface MiniService {
    val platform: ExecutionEnvironment get() = Platform
    val config: ClientConfiguration
}