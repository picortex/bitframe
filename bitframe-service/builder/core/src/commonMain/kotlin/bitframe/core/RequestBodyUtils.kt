package bitframe.core

inline fun <T, S> RequestBody.UnAuthorized<T>.map(transform: (T) -> S) = RequestBody.UnAuthorized(appId, transform(data))

inline fun <T, S> RequestBody.Authorized<T>.map(transform: (T) -> S) = RequestBody.Authorized(session, transform(data))

inline fun <T, S> RequestBody<T>.map(transform: (T) -> S) = when (this) {
    is RequestBody.Authorized -> map(transform)
    is RequestBody.UnAuthorized -> map(transform)
}