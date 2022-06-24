package bitframe.core

@Deprecated("in favour of its quivalent in bitframe.dao")
data class SortStatement(
    val property: String
) : QueryStatement