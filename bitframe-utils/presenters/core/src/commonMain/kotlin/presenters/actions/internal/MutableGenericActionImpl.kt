package presenters.actions.internal

import presenters.actions.MutableGenericAction

@PublishedApi
internal class MutableGenericActionImpl<T>(
    override val name: String,
    override var handler: (T) -> Unit
) : MutableGenericAction<T> {
    override fun setHandler(h: (T) -> Unit) {
        handler = h
    }

    override operator fun invoke(arg: T) = handler(arg)
    override fun hashCode() = name.hashCode()
    override fun toString() = "GenericAction($name)"
    override fun equals(other: Any?) = other is MutableGenericAction<*> && other.name == name
}