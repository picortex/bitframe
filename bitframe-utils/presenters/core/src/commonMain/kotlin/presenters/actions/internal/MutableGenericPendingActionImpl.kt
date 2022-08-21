package presenters.actions.internal

import koncurrent.Later
import presenters.actions.MutableGenericPendingAction

@PublishedApi
internal class MutableGenericPendingActionImpl<T>(
    override val name: String,
    override var handler: (T) -> Later<Any?>
) : MutableGenericPendingAction<T> {
    override fun onInvoked(h: (T) -> Later<Any?>) {
        handler = h
    }

    override operator fun invoke(arg: T) = handler(arg)
    override fun hashCode() = name.hashCode()
    override fun toString() = "GenericPendingAction($name)"
    override fun equals(other: Any?) = other is MutableGenericPendingAction<*> && other.name == name
}