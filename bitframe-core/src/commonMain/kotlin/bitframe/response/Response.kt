package bitframe.response

sealed class Response<out D, out I>(open val status: Status)