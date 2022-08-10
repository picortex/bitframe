package presenters.actions.internal

import presenters.actions.MutableSimpleAction

@PublishedApi
internal class MutableSimpleActionImpl(
    override val name: String,
    override var handler: () -> Unit
) : MutableSimpleAction {
    override fun setHandler(h: () -> Unit) {
        handler = h
    }

    override fun invoke() = handler()
    override fun hashCode() = name.hashCode()
    override fun toString() = "Action($name)"
    override fun equals(other: Any?) = other is MutableSimpleAction && other.name == name
}