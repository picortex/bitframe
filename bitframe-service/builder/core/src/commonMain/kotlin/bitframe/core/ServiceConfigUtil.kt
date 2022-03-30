package bitframe.core

import logging.Logger
import kotlin.properties.ReadOnlyProperty

fun ServiceConfig.logger(): ReadOnlyProperty<Any, Logger> = ReadOnlyProperty { thisRef, _ ->
    logger.with("source" to thisRef::class.simpleName)
}