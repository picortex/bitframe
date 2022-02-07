package bitframe.service.requests

import bitframe.service.Session
import kotlinx.serialization.Serializable

sealed class RequestBody<out T> {
    @Serializable
    data class Authorized<out T>(
        val session: Session.SignedIn,
        val data: T
    ) : RequestBody<T>()

    @Serializable
    data class UnAuthorized<out T>(
        val appId: String,
        val data: T
    ) : RequestBody<T>()
}