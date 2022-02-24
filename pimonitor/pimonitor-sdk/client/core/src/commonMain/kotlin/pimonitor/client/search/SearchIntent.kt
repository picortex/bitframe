package pimonitor.client.search

sealed class SearchIntent {
    object ClearSearch : SearchIntent()
    data class Search(val key: String) : SearchIntent()
}