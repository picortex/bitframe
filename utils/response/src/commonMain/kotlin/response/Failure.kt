package response

data class Failure(
    override val status: Status,
    val error: Error
) : Response<Nothing, Nothing>(status)