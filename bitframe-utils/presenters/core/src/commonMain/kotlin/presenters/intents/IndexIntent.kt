package presenters.intents

sealed class IndexIntent {
    data class Load(val uid: String) : IndexIntent()
}