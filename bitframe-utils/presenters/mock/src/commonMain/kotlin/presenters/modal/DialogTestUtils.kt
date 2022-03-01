package presenters.modal

fun <T> Dialog<T>.click(name: String) {
    val action = actions.firstOrNull {
        it.name == name
    } ?: error(
        """Action "$name" is not found in dialog "$heading".${"\n"}Available actions are ${actions.joinToString(prefix = "[", postfix = "]") { it.name }}"""
    )
    action.handler()
}