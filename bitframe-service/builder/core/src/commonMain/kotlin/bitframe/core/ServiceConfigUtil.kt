package bitframe.core

fun ServiceConfig.logger(source: Any) = logger.with("source" to source::class.simpleName)