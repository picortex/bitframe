package bitframe.http

sealed class HttpResponse<out D, out I>(open val status: HttpStatus)