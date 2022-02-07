package bitframe.service.client.requests

import bitframe.service.client.Session
import kotlinx.serialization.Serializable

sealed class RequestBody<T> {
    @Serializable
    data class Authorized<T>(
        val session: Session.SignedIn,
        val data: T
    ) : RequestBody<T>()

    @Serializable
    data class UnAuthorized<T>(
        val appId: String,
        val data: T
    ) : RequestBody<T>()
}