package bitframe.http.failure

import bitframe.http.HttpError
import bitframe.http.HttpFailure
import bitframe.http.HttpStatus
import bitframe.http.HttpSuccess
import bitframe.http.payload.decodePayloadFromString
import bitframe.http.success.payloadKey
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun Json.decodeFailureFromString(json: String): HttpFailure {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val statusMap = map[HttpFailure::status.name] as Map<String, *>
    val errorMap = map[HttpFailure::error.name] as Map<String, *>
    val status = decodeFromString(HttpStatus.serializer(), mapper.encodeToString(statusMap))
    val error = decodeFromString(HttpError.serializer(), mapper.encodeToString(errorMap))
    return HttpFailure(status, error)
}