package response.failure

import response.Error
import response.Failure
import response.Status
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun Json.decodeFailureFromString(json: String): Failure {
    val mapper = Mapper(this)
    val map = mapper.decodeFromString(json)
    val statusMap = map[Failure::status.name] as Map<String, *>
    val errorMap = map[Failure::error.name] as Map<String, *>
    val status = decodeFromString(Status.serializer(), mapper.encodeToString(statusMap))
    val error = decodeFromString(Error.serializer(), mapper.encodeToString(errorMap))
    return Failure(status, error)
}