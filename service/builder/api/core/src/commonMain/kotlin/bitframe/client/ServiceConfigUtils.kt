package bitframe.client

import bitframe.core.Session
import lexi.Logger
import kotlin.properties.ReadOnlyProperty

fun ServiceConfig.getSignedInSessionTo(action: String) = session.value as? Session.SignedIn ?: error("You must be signed in to $action")

fun ServiceConfig.logger(withSessionInfo: Boolean = false) = ReadOnlyProperty<Any, Logger> { thisRef, _ ->
    val map = buildMap {
        put("source", thisRef::class.simpleName)
        if (withSessionInfo) session.value.apply {
            user?.let {
                put("user", it.name)
                put("user-id", it.uid)
            }
            space?.let {
                put("space", it.name)
                put("space-id", it.uid)
            }
        }
    }
    logger.with(map)
}