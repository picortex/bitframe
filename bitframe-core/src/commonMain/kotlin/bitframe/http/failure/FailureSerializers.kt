package bitframe.http.failure

import bitframe.http.HttpError
import bitframe.http.HttpFailure
import bitframe.http.HttpStatus
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun Json.encodeFailureToString(failure: HttpFailure): String {
    val statusJson = encodeToString(HttpStatus.serializer(), failure.status)
    val errorJson = encodeToString(HttpError.serializer(), failure.error)
    val mapper = Mapper(this)
    val map = mapOf(
        HttpFailure::status.name to mapper.decodeFromString(statusJson),
        HttpFailure::error.name to mapper.decodeFromString(errorJson)
    )
    return mapper.encodeToString(map)
}