package presenters.modal

fun Dialog<*, *>.click(name: String) {
    if (this is ConfirmDialog && confirm.name == name) return confirm.handler()

    val action = actions.firstOrNull {
        it.name == name
    } ?: error(
        """Action "$name" is not found in dialog "$heading".${"\n"}Available actions are ${actions.joinToString(prefix = "[", postfix = "]") { it.name }}"""
    )
    action.handler()
}

fun <P> FormDialog<*, P>.clickSubmit(params: P): Unit = submit.handler(params)

fun ConfirmDialog.clickSubmit() = click("Submit")

fun <P> Dialog<*, P>.clickSubmit(with: P): Unit = when (this) {
    is ConfirmDialog -> clickSubmit()
    is FormDialog -> clickSubmit(params = with)
}