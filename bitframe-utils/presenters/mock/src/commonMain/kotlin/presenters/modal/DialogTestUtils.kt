package presenters.modal

fun Dialog.Confirm.click(name: String) {
    if (confirm.name == name) return confirm.handler()

    val action = actions.firstOrNull {
        it.name == name
    } ?: error(
        """Action "$name" is not found in dialog "$heading".${"\n"}Available actions are ${actions.joinToString(prefix = "[", postfix = "]") { it.name }}"""
    )
    action.handler()
}