package bitframe.core

@Deprecated("in favour of its quivalent in bitframe.dao")
data class LimitStatement(
    val value: Int
) : QueryStatement