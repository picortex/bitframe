package response.failure

import response.Error
import response.Failure
import response.Status
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

internal fun Json.encodeFailureToString(failure: Failure): String {
    val statusJson = encodeToString(Status.serializer(), failure.status)
    val errorJson = encodeToString(Error.serializer(), failure.error)
    val mapper = Mapper(this)
    val map = mapOf(
        Failure::status.name to mapper.decodeFromString(statusJson),
        Failure::error.name to mapper.decodeFromString(errorJson)
    )
    return mapper.encodeToString(map)
}