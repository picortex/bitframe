package bitframe.api

import bitframe.ApiConfig
import bitframe.Session
import logging.Logger
import kotlin.properties.ReadOnlyProperty

fun ApiConfig.getSignedInSessionTo(action: String) = session.value as? Session.SignedIn ?: error("You must be signed in to $action")

fun ApiConfig.logger(withSessionInfo: Boolean = false) = ReadOnlyProperty<Any, Logger> { thisRef, _ ->
    val map = buildMap {
        put("source", thisRef::class.simpleName)
        if (withSessionInfo) session.value.apply {
            user?.let { put("user", """(id=${it.uid}, name=${it.name})""") }
            space?.let { put("space", """(id=${it.uid}, name=${it.name})""") }
        }
    }
    logger.with(map)
}