package response

import io.ktor.http.*

class ResponseBuilder<D, I> {
    private var status = HttpStatusCode.OK
    private var data: D? = null
    private var info: I? = null

    private var failure: Response<D, I>? = null

    fun resolve(with: D, code: HttpStatusCode = HttpStatusCode.OK) {
        data = with
        status = code
    }

    fun resolve(with: D, info: I, code: HttpStatusCode = HttpStatusCode.OK) {
        data = with
        this.info = info
        status = code
    }

    fun badRequest(message: String): Nothing = reject(HttpStatusCode.BadRequest, Throwable(message))

    fun reject(message: String): Nothing = badRequest(message)

    fun reject(code: HttpStatusCode, message: String): Nothing {
        val throwable = Throwable(message)
        failure = responseOf(Status(code), throwable)
        throw throwable
    }

    fun reject(code: HttpStatusCode, cause: Throwable, message: String? = null): Nothing {
        failure = responseOf(Status(code), cause, message)
        throw cause
    }

    fun response(): Response<D, I> = try {
        val f = failure
        if (f != null) f else {
            val d = data ?: throw IllegalStateException(
                "looks like resolve was not called, the response builder has no data"
            )
            val res = when (val i = info) {
                null -> responseOf<D>(status, d)
                else -> responseOf<D, I>(status, d, i)
            }
            res as Response<D, I>
        }
    } catch (cause: Throwable) {
        responseOf(
            status = Status(HttpStatusCode.InternalServerError),
            cause = cause,
            message = "Couldn't obtain response"
        )
    }
}