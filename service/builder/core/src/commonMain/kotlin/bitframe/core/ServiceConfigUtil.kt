package bitframe.core

import lexi.Logger
import kotlin.properties.ReadOnlyProperty

fun ServiceConfig.logger(): ReadOnlyProperty<Any, Logger> = ReadOnlyProperty { thisRef, _ ->
    logger.with("source" to thisRef::class.simpleName)
}

fun ServiceConfig.logger(rb: RequestBody<Any?>, l: Logger = logger): ReadOnlyProperty<Any?, Logger> = ReadOnlyProperty { thisRef, _ ->
    val map = buildMap {
        val appId = when (rb) {
            is RequestBody.Authorized -> {
                val session = rb.session
                session.user.let {
                    put("user", it.name)
                    put("user-id", it.uid)
                }
                session.space.let {
                    put("space", it.name)
                    put("space-id", it.uid)
                }
                put("params", rb.data)
                session.app.uid
            }
            is RequestBody.UnAuthorized -> {
                put("params", rb.data)
                rb.appId
            }
        }
        put("appId", appId)
    }
    l.with(map)
}