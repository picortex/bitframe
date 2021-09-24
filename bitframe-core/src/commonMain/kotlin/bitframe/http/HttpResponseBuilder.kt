package bitframe.http

import bitframe.http.response.responseOf
import io.ktor.http.*

class HttpResponseBuilder<D, I> {
    private var data: D? = null
    private var info: I? = null

    private var failure: HttpResponse<D, I>? = null

    fun resolve(with: D) {
        data = with
    }

    fun resolve(with: D, info: I) {
        data = with
        this.info = info
    }

    fun badRequest(message: String): Nothing = reject(HttpStatusCode.BadRequest, Throwable(message))

    fun reject(code: HttpStatusCode, message: String): Nothing {
        val throwable = Throwable(message)
        failure = responseOf(HttpStatus(code), throwable)
        throw throwable
    }

    fun reject(code: HttpStatusCode, cause: Throwable, message: String? = null): Nothing {
        failure = responseOf(HttpStatus(code), cause, message)
        throw cause
    }

    fun response(): HttpResponse<D, I> = try {
        val f = failure
        if (f != null) f else {
            val d = data ?: throw IllegalStateException(
                "looks like resolve was not called, the response builder has no data"
            )
            val res = when (val i = info) {
                null -> responseOf(d)
                else -> responseOf(d, i)
            }
            res as HttpResponse<D, I>
        }
    } catch (cause: Throwable) {
        responseOf(
            status = HttpStatus(HttpStatusCode.InternalServerError),
            cause = cause,
            message = "Couldn't obtain response"
        )
    }
}