package bitframe.core

@Deprecated("In favour of bitframe.exceptions.IllegalConfiguration")
class IllegalConfiguration : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}