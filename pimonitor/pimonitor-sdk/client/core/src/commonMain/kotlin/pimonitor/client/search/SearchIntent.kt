package pimonitor.client.search

sealed class SearchIntent {
    object ClearSearch : SearchIntent()
    data class SearchDebouncing(val key: String) : SearchIntent()
    data class SearchImmediately(val key: String) : SearchIntent()
}