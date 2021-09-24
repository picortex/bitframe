package bitframe.http.payload

import bitframe.http.HttpPayload

fun <D> payloadOf(data: D) = HttpPayload.of(data)

fun <D, I> payloadOf(data: D, info: I) = HttpPayload.of(data, info)