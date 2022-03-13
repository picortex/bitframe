package presenters.containers

import kash.Money

fun <D> ChangeBox<D>?.toString() = when (this) {
    null -> ""
    else -> "$previous/$current"
}

fun ChangeBox<Money>?.toString() = when (this) {
    null -> ""
    else -> "${previous.toShortString()} / ${current.toShortString()}"
}