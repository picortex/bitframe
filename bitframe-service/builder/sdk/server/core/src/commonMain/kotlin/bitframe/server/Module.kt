package bitframe.server

@Deprecated("In favour of bitframe.Module")
interface Module {
    val name: String
    val actions: List<Action>

    fun info() = mapOf(
        "name" to name,
        "actions" to actions.map { it.info() }
    )
}