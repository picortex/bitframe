package pimonitor.core.search

import kotlinx.serialization.Serializable

@Serializable
class SearchParams(
    override val key: String
) : SearchRawParams