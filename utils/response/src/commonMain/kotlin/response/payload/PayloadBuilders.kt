package response.payload

import response.Payload

fun <D> payloadOf(data: D) = Payload.of(data)
fun <D, I> payloadOf(data: D, info: I) = Payload.of(data, info)