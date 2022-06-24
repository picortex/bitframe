package bitframe.http

fun HttpRequest.compulsoryBody(): String {
    val content = body ?: throw IllegalArgumentException("Looks like you didn't pass anything on the request boyd")
    return content.takeIf {
        it.isNotEmpty()
    } ?: throw IllegalArgumentException("Passing an empty body won't just do. Make sure your request body contains data as required")
}