package bitframe.client.configurators

sealed class ApiMode {
    data class Mock(
        val appId: String = "test_app"
    ) : ApiMode()

    data class Live(
        val appId: String,
        val url: String
    ) : ApiMode()
}