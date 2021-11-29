package bitframe.service.client

import platform.ExecutionEnvironment
import platform.Platform

interface MiniService {
    val platform: ExecutionEnvironment get() = Platform
}