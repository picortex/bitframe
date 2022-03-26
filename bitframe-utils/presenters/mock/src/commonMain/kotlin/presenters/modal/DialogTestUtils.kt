package presenters.modal

fun Dialog<*, *>.click(name: String) {
    if (this is Dialog.Confirm && confirm.name == name) return confirm.handler()

    val action = actions.firstOrNull {
        it.name == name
    } ?: error(
        """Action "$name" is not found in dialog "$heading".${"\n"}Available actions are ${actions.joinToString(prefix = "[", postfix = "]") { it.name }}"""
    )
    action.handler()
}

fun <P> Dialog.Form<*, P>.clickSubmit(params: P): Unit = submit.handler(params)

fun Dialog.Confirm.clickSubmit() = click("Submit")

fun <P> Dialog<*, P>.clickSubmit(with: P): Unit = when (this) {
    is Dialog.Confirm -> clickSubmit()
    is Dialog.Form -> clickSubmit(params = with)
}