package pimonitor.client.search

sealed class SearchIntent {
    object ClearSearch : SearchIntent()
    data class Search(val mode: SearchMode, val key: String) : SearchIntent()
}