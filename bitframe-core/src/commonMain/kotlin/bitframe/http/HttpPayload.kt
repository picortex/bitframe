package bitframe.http

class HttpPayload<out I, out D>(
    val info: I,
    val data: D
)