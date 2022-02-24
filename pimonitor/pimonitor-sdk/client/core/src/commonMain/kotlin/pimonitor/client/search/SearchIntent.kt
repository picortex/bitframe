package pimonitor.client.search

sealed class SearchIntent {
    object ClearResults : SearchIntent()
    data class Search(val key: String) : SearchIntent()
}