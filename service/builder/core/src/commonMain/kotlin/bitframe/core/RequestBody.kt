@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlinx.serialization.Serializable
import platform.ExecutionEnvironment
import platform.Platform
import kotlin.js.JsExport

@Deprecated("In favour of bitframe.RequestBody")
@Serializable
sealed class RequestBody<out T> {
    abstract val data: T
    val platform: ExecutionEnvironment = Platform

    @Serializable
    data class Authorized<out T>(
        val session: Session.SignedIn,
        override val data: T
    ) : RequestBody<T>()

    @Serializable
    data class UnAuthorized<out T>(
        val appId: String,
        override val data: T
    ) : RequestBody<T>()
}