package presenters.containers

fun <D> ChangeBox<D>?.toString() = when (this) {
    null -> ""
    else -> "$precursor/$successor"
}