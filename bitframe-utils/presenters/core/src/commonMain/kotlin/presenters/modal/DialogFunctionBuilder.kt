package presenters.modal

fun <T> dialog(
    heading: String,
    details: String,
    content: T,
    initializer: DialogBuilder.() -> Unit
): Dialog<T> {
    val builder = DialogBuilder().apply(initializer)
    return Dialog(heading, details, content, builder.actions)
}

fun alertDialog(
    heading: String,
    details: String,
    initializer: DialogBuilder.() -> Unit
) = dialog(heading, details, Alert, initializer)

fun confirmDialog(
    heading: String,
    details: String,
    initializer: DialogBuilder.() -> Unit
) = dialog(heading, details, Confirm, initializer)